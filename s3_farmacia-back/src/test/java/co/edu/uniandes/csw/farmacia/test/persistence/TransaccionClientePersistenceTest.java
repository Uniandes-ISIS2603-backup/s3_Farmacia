/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
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
 * @author RAMÓN
 */
@RunWith(Arquillian.class)
public class TransaccionClientePersistenceTest {
      //el manejador manda un objeto de la persistencia
    @Inject
    private TransaccionClientePersistence transaccionClientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TransaccionClienteEntity> data= new ArrayList<TransaccionClienteEntity>();
    
    private List<ClienteEntity> dataCliente =new ArrayList<ClienteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionClienteEntity.class.getPackage())
                .addPackage(TransaccionClientePersistence.class.getPackage())
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
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
       private void clearData() 
   {
        em.createQuery("delete from TransaccionClienteEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
       
       private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            dataCliente.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            TransaccionClienteEntity entity = factory.manufacturePojo(TransaccionClienteEntity.class);
            if (i == 0) {
                entity.setCliente(dataCliente.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
   @Test
    public void createTransaccionClienteTest()
    {
        //Crea objetos aleatorios
     
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionClienteEntity newEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
        TransaccionClienteEntity result = transaccionClientePersistence.create(newEntity);

        Assert.assertNotNull(result);

        TransaccionClienteEntity entity = em.find(TransaccionClienteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getMonto(), entity.getMonto());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(newEntity.getTiempo(), entity.getTiempo());  
        
        
    }
 @Test
     public void getTransaccionClienteTest() 
     {
        TransaccionClienteEntity entity = data.get(0);
        TransaccionClienteEntity newEntity = transaccionClientePersistence.find(dataCliente.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCliente(), newEntity.getCliente());
        Assert.assertEquals(entity.getMonto(), newEntity.getMonto());
        Assert.assertEquals(entity.getTiempo(), newEntity.getTiempo());
        Assert.assertEquals(entity.getTipoDePago(), newEntity.getTipoDePago());
    }  
  
    @Test
    public void deleteTransaccionTest() 
    {
        TransaccionClienteEntity entity = data.get(0);
        transaccionClientePersistence.delete(entity.getId());
        TransaccionClienteEntity deleted = em.find(TransaccionClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
       public void updateRegistroTest() {
       TransaccionClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionClienteEntity newEntity = factory.manufacturePojo(TransaccionClienteEntity.class);

        newEntity.setId(entity.getId());

        transaccionClientePersistence.update(newEntity);

        TransaccionClienteEntity resp = em.find(TransaccionClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMonto(), resp.getMonto());
        Assert.assertEquals(newEntity.getTiempo(), resp.getTiempo());
        Assert.assertEquals(newEntity.getTipoDePago(), resp.getTipoDePago());
    }
}
