/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.RegistroLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
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
public class RegistroLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private RegistroLogic registroLogic;
    
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<RegistroEntity> data = new ArrayList<RegistroEntity>();

    private List<ProductoEntity> dataProducto = new ArrayList<ProductoEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegistroEntity.class.getPackage())
                .addPackage(RegistroLogic.class.getPackage())
                .addPackage(RegistroPersistence.class.getPackage())
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
        em.createQuery("delete from RegistroEntity").executeUpdate();
        em.createQuery("delete from ProductoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProductoEntity entity = factory.manufacturePojo(ProductoEntity.class);
            em.persist(entity);
            dataProducto.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            RegistroEntity entity = factory.manufacturePojo(RegistroEntity.class);
            entity.setProducto(dataProducto.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Registro.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void createRegistroTest() throws BusinessLogicException {
        RegistroEntity newEntity = factory.manufacturePojo(RegistroEntity.class);
        newEntity.setProducto(dataProducto.get(1));
        newEntity.setTipoRegistro("ORDEN_REAPROVISONAMIENTO");
        RegistroEntity result = registroLogic.createRegistro( newEntity);
        newEntity.setProducto(dataProducto.get(1));
        Assert.assertNotNull(result);
        RegistroEntity entity = em.find(RegistroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipoRegistro(), entity.getTipoRegistro());
        Assert.assertEquals(newEntity.getCantidad(), entity.getCantidad());
    }
    
    /**
     * Prueba para crear un Registro.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createRegistroTipoRegistroInvalidoTest() throws BusinessLogicException {
        RegistroEntity newEntity = factory.manufacturePojo(RegistroEntity.class);
        newEntity.setProducto(dataProducto.get(1));
        newEntity.setTipoRegistro("ganada");
        RegistroEntity result = registroLogic.createRegistro( newEntity);
    }
    
    /**
     * Prueba para consultar la lista de Registros.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void getRegistrosTest() throws BusinessLogicException {
        List<RegistroEntity> list = registroLogic.getRegistros(dataProducto.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (RegistroEntity entity : list) {
            boolean found = false;
            for (RegistroEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Registro.
     */
    @Test
    public void getRegistroTest() {
        RegistroEntity entity = data.get(0);
        RegistroEntity resultEntity = registroLogic.getRegistro(dataProducto.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTipoRegistro(), resultEntity.getTipoRegistro());
        Assert.assertEquals(entity.getCantidad(), resultEntity.getCantidad());
    }
    
    /**
     * Prueba para actualizar un Registro.
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void updateRegistroTest() throws BusinessLogicException {
        RegistroEntity entity = data.get(0);
        RegistroEntity pojoEntity = factory.manufacturePojo(RegistroEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setTipoRegistro("robo");

        registroLogic.updateRegistro(dataProducto.get(1).getId(), pojoEntity);

        RegistroEntity resp = em.find(RegistroEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTipoRegistro(), resp.getTipoRegistro());
        Assert.assertEquals(pojoEntity.getCantidad(), resp.getCantidad());
    }
    
    /**
     * Prueba para eliminar un Registro.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteRegistroTest() throws BusinessLogicException {
        RegistroEntity entity = data.get(0);
        registroLogic.deleteRegistro(dataProducto.get(1).getId(), entity.getId());
        RegistroEntity deleted = em.find(RegistroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para eliminarle un registro a un producto del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteRegistroConProductoNoAsociadoTest() throws BusinessLogicException {
        RegistroEntity entity = data.get(0);
        registroLogic.deleteRegistro(dataProducto.get(0).getId(), entity.getId());
    }
    
}
