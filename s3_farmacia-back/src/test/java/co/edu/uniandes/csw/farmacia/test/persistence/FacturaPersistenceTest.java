/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.FacturaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class FacturaPersistenceTest {
    
    @Inject
    private FacturaPersistence facturaPersistence;
        
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i=0; i<3; i++){
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
        
   @Test
    public void createFacturaTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newFacturaEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result;
        
        result = facturaPersistence.create(newFacturaEntity);
        
        Assert.assertNotNull(result);    
        
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        
        Assert.assertEquals(newFacturaEntity.getId(), entity.getId());
        
    }
    
    @Test
    public void getFacturasTest()
    {
        List<FacturaEntity> lista = facturaPersistence.findAll();
        
        Assert.assertEquals(data.size(), lista.size());     
    }
    
    @Test 
    public void getFacturaTest()
    {
        FacturaEntity pEntity = data.get(0);
        FacturaEntity newFacturaEntity = facturaPersistence.find(pEntity.getId());
        Assert.assertNotNull(newFacturaEntity);
        Assert.assertEquals(pEntity.getId(), newFacturaEntity.getId());
        
    }
    
    @Test
    public void deleteFacturaTest()
    {
        FacturaEntity proEntity = data.get(0);
        facturaPersistence.delete(proEntity.getId());
        FacturaEntity eliminado = em.find(FacturaEntity.class, proEntity.getId());
        Assert.assertNull(eliminado);
    }
    
    @Test 
    public void updateFacturaTest()
    {
        FacturaEntity p = data.get(1);
        PodamFactory factory = new PodamFactoryImpl();
       
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        
        newEntity.setId(p.getId());
        
        facturaPersistence.update(newEntity);
        
         FacturaEntity resp = em.find(FacturaEntity.class, p.getId());
         
         Assert.assertEquals(newEntity.getId(), resp.getId());

    }
    
    
    
}
