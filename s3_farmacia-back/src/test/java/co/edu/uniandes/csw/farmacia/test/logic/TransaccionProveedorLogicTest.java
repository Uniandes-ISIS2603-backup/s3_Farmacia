/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.TransaccionProveedorLogic;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
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
 * @author jd.florezg1
 */
@RunWith(Arquillian.class)
public class TransaccionProveedorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TransaccionProveedorLogic transaccionProveedorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TransaccionProveedorEntity> data = new ArrayList<TransaccionProveedorEntity>();
    
    private List<ProveedorEntity> proveedorData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionProveedorLogic.class.getPackage())
                .addPackage(TransaccionProveedorPersistence.class.getPackage())
                .addPackage(TransaccionProveedorEntity.class.getPackage())
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
    private void clearData() {
        em.createQuery("delete from TransaccionProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProveedorEntity proveedor = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(proveedor);
            proveedorData.add(proveedor);
        }
        for (int i = 0; i < 3; i++) {
            TransaccionProveedorEntity entity = factory.manufacturePojo(TransaccionProveedorEntity.class);
            entity.setProveedor(proveedorData.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una TransaccionProveedor.
     */
    @Test
    public void createTransaccionProveedorTest() {
        TransaccionProveedorEntity newEntity = factory.manufacturePojo(TransaccionProveedorEntity.class);
        newEntity.setProveedor(proveedorData.get(1));
        TransaccionProveedorEntity result = transaccionProveedorLogic.createTransaccionProveedor( proveedorData.get(1).getId() , newEntity);
        Assert.assertNotNull(result);
        TransaccionProveedorEntity entity = em.find(TransaccionProveedorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMonto(), entity.getMonto());
    }
    
    /**
     * Prueba para consultar la lista de Transacciones proveedor.
     */
    @Test
    public void getTransaccionesProveedorTest() {
        List<TransaccionProveedorEntity> list = transaccionProveedorLogic.getTransaccionesProveedor(proveedorData.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (TransaccionProveedorEntity entity : list) {
            boolean found = false;
            for (TransaccionProveedorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        TransaccionProveedorEntity resultEntity = transaccionProveedorLogic.getTransaccionProveedor(proveedorData.get(1).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getMonto(), resultEntity.getMonto());
    }
    
    /**
     * Prueba para actualizar una TransaccionProveedor.
     */
    @Test
    public void updateTransaccionProveedorTest() {
        TransaccionProveedorEntity entity = data.get(0);
        TransaccionProveedorEntity pojoEntity = factory.manufacturePojo(TransaccionProveedorEntity.class);
        pojoEntity.setId(entity.getId());
        transaccionProveedorLogic.updateTransaccionProveedor(proveedorData.get(1).getId(), pojoEntity);
        TransaccionProveedorEntity resp = em.find(TransaccionProveedorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getMonto(), resp.getMonto());
    }
        
     /**
     * Prueba para eliminar una TransaccionProveedor.
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTransaccionProveedorTest() throws BusinessLogicException {
        TransaccionProveedorEntity entity = data.get(0);
        transaccionProveedorLogic.deleteTransaccionProveedor(proveedorData.get(1).getId(), entity.getId());
        TransaccionProveedorEntity deleted = em.find(TransaccionProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
