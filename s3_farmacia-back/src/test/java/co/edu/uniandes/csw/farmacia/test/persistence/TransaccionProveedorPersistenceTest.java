/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
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
 * @author jd.florezg1
 */
@RunWith(Arquillian.class)
public class TransaccionProveedorPersistenceTest {
    
    @Inject
    private TransaccionProveedorPersistence transaccionProveedorPersistence;

    
    @PersistenceContext
    private EntityManager em;
   @Inject
   UserTransaction utx;

    private List<TransaccionProveedorEntity> data = new ArrayList<TransaccionProveedorEntity>();
    
    private List<ProveedorEntity> dataProveedor = new ArrayList<ProveedorEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionProveedorEntity.class.getPackage())
                .addPackage(TransaccionProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest()
    {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) 
        {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
     private void clearData(){
        em.createQuery("delete from TransaccionProveedorEntity").executeUpdate();
    }
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(entity);
            dataProveedor.add(entity);
        }
        for(int i=0; i<3; i++){
            TransaccionProveedorEntity entity = factory.manufacturePojo(TransaccionProveedorEntity.class);
            em.persist(entity);
            data.add(entity);
            System.out.println(transaccionProveedorPersistence.findAll().size());
        }
    }
    
    @Test
    public void createTransaccionProveedorTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionProveedorEntity newTransaccionProveedorEntity = factory.manufacturePojo(TransaccionProveedorEntity.class);
        TransaccionProveedorEntity result = transaccionProveedorPersistence.create(newTransaccionProveedorEntity);
        
        Assert.assertNotNull(result);    
        
        TransaccionProveedorEntity entity = em.find(TransaccionProveedorEntity.class, result.getId());
        
        Assert.assertEquals(newTransaccionProveedorEntity.getMonto(), entity.getMonto());
        
    }
    
    /**
     * Prueba para consultar la lista de TransaccionesProveedor.
     */
    @Test
    public void getTransaccionesProveedorTest() {
        List<TransaccionProveedorEntity> list = transaccionProveedorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TransaccionProveedorEntity ent : list) {
            boolean found = false;
            for (TransaccionProveedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una TransaccionProveedor.
     */
    @Test
    public void getTransaccionProveedorTest() {
        TransaccionProveedorEntity entity = data.get(0);
        TransaccionProveedorEntity newEntity = transaccionProveedorPersistence.find(dataProveedor.get(0).getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getMonto(), newEntity.getMonto());
    }
    
    /**
     * Prueba para eliminar una TransaccionProveedor.
     */
    @Test
    public void deleteTransaccionProveedorTest() {
        TransaccionProveedorEntity entity = data.get(0);
        transaccionProveedorPersistence.delete(entity.getId());
        TransaccionProveedorEntity deleted = em.find(TransaccionProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una TransaccionProveedor.
     */
    @Test
    public void updateTransaccionProveedorTest() {
        TransaccionProveedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionProveedorEntity newEntity = factory.manufacturePojo(TransaccionProveedorEntity.class);

        newEntity.setId(entity.getId());

        transaccionProveedorPersistence.update(newEntity);

        TransaccionProveedorEntity resp = em.find(TransaccionProveedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMonto(), resp.getMonto());
    }

}
