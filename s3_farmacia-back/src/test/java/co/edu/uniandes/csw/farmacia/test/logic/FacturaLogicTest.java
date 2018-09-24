/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.ClienteLogic;
import co.edu.uniandes.csw.farmacia.ejb.FacturaLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import co.edu.uniandes.csw.farmacia.persistence.FacturaPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author df.machado10
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;

    @Inject
    private TransaccionClienteLogic transLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TransaccionClienteEntity> transData = new ArrayList<>();

    private List<FacturaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(TransaccionClienteEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++)
        {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            TransaccionClienteEntity transEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
            em.persist(transEntity);
            entity.setTransaccionCLiente(transEntity);
            transEntity.setFactura(entity);
            em.persist(entity);
            data.add(entity);
            transData.add(transEntity);
        }
        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        em.persist(cliente);
        cliente.getTransaccionesCliente().add(transData.get(2));
        transData.get(2).setCliente(cliente);
    }
    
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity factEntity = factory.manufacturePojo(FacturaEntity.class);
        TransaccionClienteEntity transaccionClienteEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
        
        transaccionClienteEntity = transLogic.createTransaccionCliente(transData.get(2).getCliente().getId(), transaccionClienteEntity);
        factEntity.setTransaccionCLiente(transaccionClienteEntity);
        FacturaEntity result = facturaLogic.createFactura(factEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(factEntity.getId(), entity.getId());
        Assert.assertEquals(factEntity.getPrecio(), entity.getPrecio());
    }
}
