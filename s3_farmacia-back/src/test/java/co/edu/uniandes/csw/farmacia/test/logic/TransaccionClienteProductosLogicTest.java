/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteProductosLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author fj.gonzalez
 */
@RunWith(Arquillian.class)
public class TransaccionClienteProductosLogicTest {
    
     @Inject
    private TransaccionClienteLogic transaccionClienteLogic;


    @Inject
    private TransaccionClienteProductosLogic transaccionClienteProductoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    private List<ProductoEntity> dataProducto = new ArrayList<>();

    private List<ClienteEntity> dataCliente = new ArrayList<>();

    private PodamFactory factory = new PodamFactoryImpl();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ProductoEntity.class.getPackage())
                .addPackage(TransaccionClienteEntity.class.getPackage())
                .addPackage(TransaccionClienteProductosLogic.class.getPackage())
                .addPackage(TransaccionProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            ut.begin();
            clearData();
            insertData();
            ut.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                ut.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ProductoEntity").executeUpdate();
        em.createQuery("delete from TransaccionClienteEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    private void insertData() {
        
        ProductoEntity productos = factory.manufacturePojo(ProductoEntity.class);
        em.persist(productos);
        dataProducto.add(productos);

        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        em.persist(cliente);

        TransaccionClienteEntity entity = factory.manufacturePojo(TransaccionClienteEntity.class);
        em.persist(entity);
        entity.setCliente(cliente);
        List<TransaccionClienteEntity> lista = new ArrayList<>();
        lista.add(entity);
        cliente.setTransaccionesCliente(lista);
        dataCliente.add(cliente);

        TransaccionClienteEntity en = transaccionClienteLogic.createTransaccionCliente(dataCliente.get(0).getId(), entity);

    }

    @Test
    public void asociateTest() {
        Long idTransaccion = new Long(0);
        try {
            idTransaccion = dataCliente.get(0).getTransaccionesCliente().get(0).getId();
            transaccionClienteProductoLogic.asociate(dataCliente.get(0).getId(), idTransaccion, new Long(170000));
        } catch (Exception e) {
            Assert.assertEquals("No se encontró el elemento", e.getMessage());
            try {

                transaccionClienteProductoLogic.asociate(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId(), dataProducto.get(0).getId());
                TransaccionClienteEntity entity = em.find(TransaccionClienteEntity.class, idTransaccion);
                Assert.assertNotNull(entity.getProductos());
                Assert.assertEquals(dataProducto.get(0).getId(), entity.getProductos().get(0).getId());

            } catch (Exception ex) {
                Assert.fail("No debería haber tirado excepcion" );
            }

        }
    }

    @Test
    public void deasociateTest() {
        Long idTransaccion = new Long(0);
        try {
            idTransaccion = dataCliente.get(0).getTransaccionesCliente().get(0).getId();
            transaccionClienteProductoLogic.deasociate(dataCliente.get(0).getId(), idTransaccion, new Long(170000));
        } catch (Exception e) {
            Assert.assertEquals( "No se encontró el elemento", e.getMessage());

            try {
                transaccionClienteProductoLogic.deasociate(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId(), dataProducto.get(0).getId());
            } catch (Exception ep) {
                    Assert.assertEquals( "No existe la asociación", ep.getMessage());
                try {
                    transaccionClienteProductoLogic.asociate(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId(), dataProducto.get(0).getId());
                    TransaccionClienteEntity entity = em.find(TransaccionClienteEntity.class, idTransaccion);
                    Assert.assertNotNull(entity.getProductos());
                    Assert.assertEquals(dataProducto.get(0).getId(), entity.getProductos().get(0).getId());
                     transaccionClienteProductoLogic.deasociate(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId(), dataProducto.get(0).getId());
                     entity = em.find(TransaccionClienteEntity.class, idTransaccion);
                     Assert.assertEquals(0, entity.getProductos().size());                     
                    
                } catch (Exception ex) {
                    Assert.fail("No debería haber tirado excepcion");
                }
            }

        }
    }
    @Test
    public void getProductosTest()
    {
        try {
          transaccionClienteProductoLogic.getProductos(new Long(-1),new Long( -1));
        } catch (BusinessLogicException ex)
        {
            try {
                Assert.assertEquals("No se encontró la transacción del cliente", ex.getMessage());
                transaccionClienteProductoLogic.asociate(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId(), dataProducto.get(0).getId());
                List<ProductoEntity> l = transaccionClienteProductoLogic.getProductos(dataCliente.get(0).getId(), dataCliente.get(0).getTransaccionesCliente().get(0).getId());
                Assert.assertEquals(1, l.size());
               List<TransaccionClienteEntity> list =
                       transaccionClienteProductoLogic.getTransacciones(dataProducto.get(0).getId());
               Assert.assertEquals(1, list.size());
               Assert.assertEquals(dataCliente.get(0).getTransaccionesCliente().get(0).getId(), list.get(0).getId());

            } catch (BusinessLogicException ex1) {
                    Assert.fail("No debería haber tirado excepcion");
            }
         
        }
    }
    
}
