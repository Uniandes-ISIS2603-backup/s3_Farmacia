/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ProductoPersistenceTest {
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private ProductoPersistence productoPersistence;
    
    @Inject
    UserTransaction utx;
    
    private List<ProductoEntity> listaDePrueba = new ArrayList<ProductoEntity>();
    
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProductoEntity.class.getPackage())
                .addPackage(ProductoPersistence.class.getPackage())
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
        em.createQuery("delete from ProductoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {

            ProductoEntity productoEntity = factory.manufacturePojo(ProductoEntity.class);

            em.persist(productoEntity);

            listaDePrueba.add(productoEntity);
        }
    }
    
   @Test
    public void createProveedorTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        ProductoEntity newProductoEntity = factory.manufacturePojo(ProductoEntity.class);
        ProductoEntity result = productoPersistence.create(newProductoEntity);
        
        Assert.assertNotNull(result);    
        
        ProductoEntity entity = em.find(ProductoEntity.class, result.getId());
        
        Assert.assertEquals(newProductoEntity.getCantidad(), entity.getCantidad());
        
    }
    @Test
    public void getProveedoresTest()
    {
        List<ProductoEntity> lista = productoPersistence.list();
        
        Assert.assertEquals(listaDePrueba.size(), lista.size());
        //para la prueba implementaremos un arreglo de proveedores.
        
    }
    @Test 
    public void getProveedorTest()
    {
        ProductoEntity pEntity = listaDePrueba.get(0);
        ProductoEntity newProductoEntity = productoPersistence.find(pEntity.getId());
        Assert.assertNotNull(newProductoEntity);
        Assert.assertEquals(pEntity.getId(), newProductoEntity.getId());
        
    }
    @Test
    public void deleteProveedorTest()
    {
        ProductoEntity proEntity = listaDePrueba.get(0);
        productoPersistence.delete(proEntity.getId());
        ProductoEntity eliminado = em.find(ProductoEntity.class, proEntity.getId());
        Assert.assertNull(eliminado);
    }
    @Test 
    public void updateProveedorTest()
    {
        ProductoEntity p = listaDePrueba.get(1);
        PodamFactory factory = new PodamFactoryImpl();
        ProductoEntity newEntity = factory.manufacturePojo(ProductoEntity.class);
        
        newEntity.setId(p.getId());
        
        productoPersistence.update(newEntity);
        
         ProductoEntity resp = em.find(ProductoEntity.class, p.getId());
         
         Assert.assertEquals(newEntity.getId(), resp.getId());

    }
}
