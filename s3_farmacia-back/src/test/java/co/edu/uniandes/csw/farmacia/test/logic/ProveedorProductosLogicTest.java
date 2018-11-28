/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.ProveedorLogic;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorProductosLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
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
import static org.junit.Assert.fail;
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
public class ProveedorProductosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProveedorLogic proveedorLogic;

    @Inject
    private ProveedorProductosLogic proveedorProductosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    private List<ProductoEntity> dataProducto = new ArrayList<>();

    private List<ProveedorEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProductoEntity.class.getPackage())
                .addPackage(ProveedorProductosLogic.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProductoEntity productos = factory.manufacturePojo(ProductoEntity.class);
            em.persist(productos);
            dataProducto.add(productos);
        }
        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);
            List<ProveedorEntity> proveedores = new ArrayList<>();
            proveedores.add(entity);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                dataProducto.get(i).setProveedores(proveedores);
            }
        }
    }

    /**
     * Test que verifica la obtención de una lista de productos asociada a un
     * proveedor.
     */
    @Test
    public void getProductosTest() {
        List<ProductoEntity> list = proveedorProductosLogic.getProductos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Test que verifica la obtención de un producto asociada a un proveedor.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void getProductoTest() throws BusinessLogicException {
        ProveedorEntity enti = data.get(0);
        ProductoEntity producEntity = dataProducto.get(0);
        ProductoEntity producEntityRpta = proveedorProductosLogic.getProducto(enti.getId(), producEntity.getId());

        Assert.assertEquals(producEntity.getId(), producEntityRpta.getId());
        Assert.assertEquals(producEntity.getPrecio(), producEntityRpta.getPrecio());
        Assert.assertEquals(producEntity.getCantidad(), producEntityRpta.getCantidad());
        Assert.assertEquals(producEntity.getTipoProducto(), producEntityRpta.getTipoProducto());
        Assert.assertEquals(producEntity.getPerecedero(), producEntityRpta.getPerecedero());

    }

    @Test
    public void addProductoTest() {
        ProveedorEntity entity = data.get(0);
        ProductoEntity productoEntity = dataProducto.get(1);
        ProductoEntity response = proveedorProductosLogic.addProducto(entity.getId(), productoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(productoEntity.getId(), response.getId());
        
        proveedorProductosLogic.removeProducto(entity.getId(), response.getId());
        try {
           ProductoEntity p =  proveedorProductosLogic.getProducto(entity.getId(), productoEntity.getId());
           Assert.assertNull(p);
            fail("No deberia haber logrado obtenerlo");
        } catch (BusinessLogicException ex) 
        {
            Assert.assertEquals("El producto no está asociado al proveedor.", ex.getMessage());
            int t = proveedorProductosLogic.productosNoAniadidos(entity.getId()).size();
            Assert.assertEquals(2, t);
        }   
    }

    @Test(expected = BusinessLogicException.class)
    public void getProductoNoAsociadoTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        ProductoEntity productoEntity = dataProducto.get(1);
        proveedorProductosLogic.getProducto(entity.getId(), productoEntity.getId());
    }

    @Test
    public void replaceProductosTest() {
        ProveedorEntity entity = data.get(0);
        List<ProductoEntity> list = dataProducto.subList(1, 3);
        proveedorProductosLogic.replaceProductos(entity.getId(), list);

        entity = proveedorLogic.getProveedor(entity.getId());
        Assert.assertTrue(entity.getProductos().contains(dataProducto.get(0)));
        Assert.assertTrue(entity.getProductos().contains(dataProducto.get(1)));
        Assert.assertTrue(entity.getProductos().contains(dataProducto.get(2)));
    }

}
