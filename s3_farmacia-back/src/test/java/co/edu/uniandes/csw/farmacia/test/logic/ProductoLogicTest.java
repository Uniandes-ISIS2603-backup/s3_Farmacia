/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.ProductoLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
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
public class ProductoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private RegistroPersistence rp;
    
    @Inject
    private ProductoLogic productoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    private List<ProductoEntity> productosData;
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProductoEntity.class.getPackage())
                .addPackage(ProductoLogic.class.getPackage())
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
    
     private void insertData()
    {
        productosData = new ArrayList<ProductoEntity>();
        for (int i = 0; i < 5; i++)
        {
            ProductoEntity producto = factory.manufacturePojo(ProductoEntity.class);
            em.persist(producto);
            productosData.add(producto);   
        }
    }
    
    private void clearData() 
   {
        em.createQuery("delete from RegistroEntity").executeUpdate();
        em.createQuery("delete from ProductoEntity").executeUpdate();
    }

    @Test
    public void crearProductoTest() throws BusinessLogicException {
        ProductoEntity producto = factory.manufacturePojo(ProductoEntity.class);
        RegistroEntity registro = factory.manufacturePojo(RegistroEntity.class);
        RegistroEntity registroFinal = rp.create(registro);
        ProductoEntity fin = productoLogic.create(producto, registroFinal.getId());
        Assert.assertNotNull(fin);
        ProductoEntity verificacion = em.find(ProductoEntity.class, fin.getId());
        Assert.assertEquals(producto.getId(), verificacion.getId());
        Assert.assertEquals(producto.getUnidadesDisponibles(),
                verificacion.getUnidadesDisponibles());
        
    }
    
    @Test
    public void buscarProductoTest() throws BusinessLogicException {
        List<ProductoEntity> list = productoLogic.list();
        Assert.assertEquals(productosData.size(), list.size());
        for (ProductoEntity entity : list) {
            boolean found = false;
            for (ProductoEntity storEntity : productosData) {
                if (storEntity.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getProductoTest() throws BusinessLogicException {
        ProductoEntity producto = productosData.get(1);
        ProductoEntity result = productoLogic.get(producto.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), producto.getId());
        Assert.assertEquals(result.getUnidadesDisponibles(),
                producto.getUnidadesDisponibles());
    }
    
    @Test
    public void deleteProductoTest() throws BusinessLogicException {
        ProductoEntity producto = productosData.get(1);
        productoLogic.delete(producto.getId());
        try {
            ProductoEntity deleted = productoLogic.get(producto.getId());
            Assert.fail();
        } catch (BusinessLogicException ble) {
            
        }
    }
    
    @Test
    public void updateProductoTest () throws BusinessLogicException {
        ProductoEntity producto = productosData.get(0);
        ProductoEntity pojo = factory.manufacturePojo(ProductoEntity.class);
        pojo.setId(producto.getId());
        productoLogic.update(pojo.getId(), pojo);
        ProductoEntity resultado = em.find(ProductoEntity.class, 
                producto.getId());
        Assert.assertEquals(pojo.getId(), resultado.getId());
        Assert.assertEquals(pojo.getUnidadesDisponibles(), 
                resultado.getUnidadesDisponibles());
    }
    
}
