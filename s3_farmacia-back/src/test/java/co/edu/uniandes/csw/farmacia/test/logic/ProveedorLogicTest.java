/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.ProveedorLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author fj.gonzalez
 */
@RunWith(Arquillian.class)
public class ProveedorLogicTest
{
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProveedorLogic proveedorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProveedorEntity> data = new ArrayList<>();
    
    private List<ProductoEntity> productosData = new ArrayList<>();
    
     private List<TransaccionProveedorEntity> transaccionesData = new ArrayList<>();

    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
      @Before
    public void configTest() {
        try {
            utx.begin();
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from ProductoEntity").executeUpdate();
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for (int i = 0; i < 5; i++)
        {
            ProductoEntity producto = factory.manufacturePojo(ProductoEntity.class);
            em.persist(producto);
            productosData.add(producto);   
        }
        for(int i=0; i < 3 ; i++)
        {
            TransaccionProveedorEntity transaccion = factory.manufacturePojo(TransaccionProveedorEntity.class);
            em.persist(transaccion);
            transaccionesData.add(transaccion);
        }
        for (int i = 0; i < 5; i++)
        {
            ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(proveedor);
            data.add(proveedor);
            List<ProveedorEntity> proveedores = new ArrayList<ProveedorEntity>();
            proveedores.add(proveedor);
            if(i==2)
            {
                productosData.get(i).setProveedor(proveedores);
              //  transaccionesData.get(i).setProveedor(proveedor);
            }
            
        }
    }
    @Test
    public void createProveedorTest() throws BusinessLogicException
    {
        ProveedorEntity e = factory.manufacturePojo(ProveedorEntity.class);
        System.out.println(proveedorLogic);
        ProveedorEntity definitiva = proveedorLogic.createProveedor(e);
        Assert.assertNotNull(definitiva);
        ProveedorEntity verificacion = em.find(ProveedorEntity.class, definitiva.getId());
        Assert.assertEquals(e.getId(), verificacion.getId());
        Assert.assertEquals(e.getNombre(), verificacion.getNombre());
        
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProveedorConMismoNombreTest() throws BusinessLogicException 
    {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        proveedorLogic.createProveedor(newEntity);
    }
    
    @Test
    public void getProveedoresTest()
    {
        List<ProveedorEntity> listi = proveedorLogic.getProveedores();
        Assert.assertEquals(data.size(), listi.size());
        for(ProveedorEntity entity : listi)
        {
            boolean found = false;
            for(ProveedorEntity storEntity : data)
            {
                if(entity.getId().equals(storEntity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getProveedorTest()
    {
        ProveedorEntity entity = data.get(1);
        ProveedorEntity resul = proveedorLogic.getProveedor(entity.getId());
        Assert.assertNotNull(resul);
        Assert.assertEquals(entity.getId(), resul.getId());
        Assert.assertEquals(entity.getNombre(), resul.getNombre());
    }
    @Test
    public void updateProveedorTest()
    {
        ProveedorEntity provEntity = data.get(0);
        ProveedorEntity entityPojo = factory.manufacturePojo(ProveedorEntity.class);
        entityPojo.setId(provEntity.getId());
        proveedorLogic.updateProveedor(entityPojo.getId(), entityPojo);
        ProveedorEntity resultado = em.find(ProveedorEntity.class, provEntity.getId());
        Assert.assertEquals(entityPojo.getId(), resultado.getId());
        Assert.assertEquals(entityPojo.getNombre(), resultado.getNombre());
    }
    @Test
    public void deleteProveedorTest() throws BusinessLogicException 
    {
         ProveedorEntity entity = data.get(1);
        proveedorLogic.deleteProveedor(entity.getId());
        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test(expected = BusinessLogicException.class)
   public void deleteProveedorConProductosTest() throws BusinessLogicException
    {
        ProveedorEntity ent = data.get(2);
        proveedorLogic.deleteProveedor(ent.getId());
    }


    

    
    
}
