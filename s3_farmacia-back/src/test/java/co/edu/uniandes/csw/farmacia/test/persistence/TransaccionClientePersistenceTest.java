/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

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
    private TransaccionClientePersistence TransaccionClientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TransaccionClienteEntity> listaDePrueba= new ArrayList<TransaccionClienteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionClienteEntity.class.getPackage())
                .addPackage(TransaccionClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
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
       private void clearData() 
   {
        em.createQuery("delete from TransaccionClienteEntity").executeUpdate();
    }
       
       private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {

            TransaccionClienteEntity transaccionEntity = factory.manufacturePojo(TransaccionClienteEntity.class);

            em.persist(transaccionEntity);

            listaDePrueba.add(transaccionEntity);
        }
    }
   @Test
    public void createTransaccionClienteTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionClienteEntity newTransaccionClienteEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
        TransaccionClienteEntity result = TransaccionClientePersistence.create(newTransaccionClienteEntity);
        Assert.assertNotNull(result);        
        
    }
 @Test
     public void getRegistrosTest() 
     {
        List<TransaccionClienteEntity> list = TransaccionClientePersistence.findAll();
        Assert.assertEquals(listaDePrueba.size(), list.size());
        for (TransaccionClienteEntity ent : list) {
            boolean found = false;
            for (TransaccionClienteEntity entity : listaDePrueba) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }  
     @Test
    public void getTransaccionTest() 
    {
        TransaccionClienteEntity entity = listaDePrueba.get(0);
        TransaccionClienteEntity newEntity = TransaccionClientePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTipoDePago(), newEntity.getTipoDePago());
        Assert.assertEquals(entity.GetMonto(), newEntity.GetMonto());
        Assert.assertEquals(entity.getParcial(), newEntity.getParcial());
        Assert.assertEquals(entity.getTiempo(), newEntity.getTiempo()); 
        
    }
    @Test
    public void deleteRegistroTest() 
    {
        TransaccionClienteEntity entity = listaDePrueba.get(0);
        TransaccionClientePersistence.delete(entity.getId());
        TransaccionClienteEntity deleted = em.find(TransaccionClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
       public void updateRegistroTest() {
        TransaccionClienteEntity entity = listaDePrueba.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionClienteEntity newEntity = factory.manufacturePojo(TransaccionClienteEntity.class);

        newEntity.setId(entity.getId());

        TransaccionClientePersistence.update(newEntity);

        TransaccionClienteEntity resp = em.find(TransaccionClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.GetMonto(), resp.GetMonto());
        Assert.assertEquals(newEntity.getParcial(), resp.getParcial());
        Assert.assertEquals(newEntity.getTiempo(), resp.getTiempo());
        Assert.assertEquals(newEntity.getTipoDePago(), resp.getTipoDePago());
    }
}
