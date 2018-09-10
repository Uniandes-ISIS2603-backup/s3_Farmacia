/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
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
 * @author francisco
 */
@RunWith(Arquillian.class)
public class ProveedorPersistenceTest 
{
    //el manejador manda un objeto de la persistencia
    @Inject
    private ProveedorPersistence proveedorPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ProveedorEntity> listaDePrueba = new ArrayList<ProveedorEntity>();
    
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
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
    private void clearData() 
   {
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {

            ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);

            em.persist(proveedorEntity);

            listaDePrueba.add(proveedorEntity);
        }
    }
    
   @Test
    public void createProveedorTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newProveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result = proveedorPersistence.create(newProveedorEntity);
        
        Assert.assertNotNull(result);    
        
        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());
        
        Assert.assertEquals(newProveedorEntity.getNombre(), entity.getNombre());
        
    }
    @Test
    public void getProveedoresTest()
    {
        List<ProveedorEntity> lista = proveedorPersistence.findAll();
        
        Assert.assertEquals(listaDePrueba.size(), lista.size());
        //para la prueba implementaremos un arreglo de proveedores.
        
    }
    @Test 
    public void getProveedorTest()
    {
        ProveedorEntity pEntity = listaDePrueba.get(0);
        ProveedorEntity newProveedorEntity = proveedorPersistence.find(pEntity.getId());
        Assert.assertNotNull(newProveedorEntity);
        Assert.assertEquals(pEntity.getNombre(), newProveedorEntity.getNombre());
        
    }
    @Test
    public void deleteProveedorTest()
    {
        ProveedorEntity proEntity = listaDePrueba.get(0);
        proveedorPersistence.delete(proEntity.getId());
        ProveedorEntity eliminado = em.find(ProveedorEntity.class, proEntity.getId());
        Assert.assertNull(eliminado);
    }
    @Test 
    public void updateProveedorTest()
    {
        ProveedorEntity p = listaDePrueba.get(1);
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        
        newEntity.setId(p.getId());
        
        proveedorPersistence.update(newEntity);
        
         ProveedorEntity resp = em.find(ProveedorEntity.class, p.getId());
         
         Assert.assertEquals(newEntity.getNombre(), resp.getNombre());

    }
    @Test
    public void findProveedorByNameTest()
    {
        ProveedorEntity proveedorEntity = listaDePrueba.get(0);
        ProveedorEntity nuevaEntidad = proveedorPersistence.findByName(proveedorEntity.getNombre());
        Assert.assertNotNull(nuevaEntidad);
        Assert.assertEquals(proveedorEntity.getNombre(), nuevaEntidad.getNombre());
        
        nuevaEntidad = proveedorPersistence.findByName(null);
        Assert.assertNull(nuevaEntidad);
    }

    
    
}
