/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
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
public class RegistroPersistenceTest {
    
     //el manejador manda un objeto de la persistencia
    @Inject
    private RegistroPersistence registroPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<RegistroEntity> data = new ArrayList<RegistroEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegistroEntity.class.getPackage())
                .addPackage(RegistroPersistence.class.getPackage())
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
        em.createQuery("delete from RegistroEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i=0; i<3; i++){
            RegistroEntity entity = factory.manufacturePojo(RegistroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createRegistroTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity newRegistroEntity = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity result = registroPersistence.create(newRegistroEntity);
        
        Assert.assertNotNull(result);     
        RegistroEntity entity = em.find(RegistroEntity.class, result.getId());
        Assert.assertEquals(newRegistroEntity.getTipoRegistro(),entity.getTipoRegistro());
    }
}
