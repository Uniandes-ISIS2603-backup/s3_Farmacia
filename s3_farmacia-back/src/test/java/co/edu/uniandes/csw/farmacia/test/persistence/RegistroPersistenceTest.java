/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
import com.sun.messaging.jmq.util.log.SysLog;
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
    
    private List<ProductoEntity> dataProducto = new ArrayList<ProductoEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RegistroEntity.class.getPackage())
                .addPackage(RegistroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData(){
        em.createQuery("delete from RegistroEntity").executeUpdate();
        em.createQuery("delete from ProductoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ProductoEntity entity = factory.manufacturePojo(ProductoEntity.class);
            em.persist(entity);
            dataProducto.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            RegistroEntity entity = factory.manufacturePojo(RegistroEntity.class);
            if (i == 0) {
                entity.setProducto(dataProducto.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba el metodo de listar los registros
     */
        @Test
    public void listTest()
    {  
        List<RegistroEntity> respuesta = registroPersistence.list();
        Assert.assertNotNull(respuesta.size());
    }
    
    /**
     * Prueba para crear un regsitro
     */
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
        Assert.assertEquals(newRegistroEntity.getCantidad(),entity.getCantidad());
    }
    
    /**
     * Prueba para encontrar los registros de un producto
     */
    @Test
    public void findRegistrosTest()
    {
        ProductoEntity entity = dataProducto.get(0);
        List<RegistroEntity> lista = registroPersistence.findRegistros(entity.getId());
        entity.setRegistros(registroPersistence.findRegistros(entity.getId()));
        Assert.assertEquals(entity.getRegistros(), lista);
    }
    
    
    /**
     * Prueba para consultar un Registro.
     */
     
    @Test
    public void getRegistroTest() {
        RegistroEntity entity = data.get(0);
        RegistroEntity newEntity = registroPersistence.find(dataProducto.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTipoRegistro(), newEntity.getTipoRegistro());
        Assert.assertEquals(entity.getCantidad(), newEntity.getCantidad());
    }
    


    /**
     * Prueba para eliminar un Registro.
     */
    @Test
    public void deleteRegistroTest() {
        RegistroEntity entity = data.get(0);
        registroPersistence.delete(entity.getId());
        RegistroEntity deleted = em.find(RegistroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Registro.
     */
    @Test
    public void updateRegistroTest() {
        RegistroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RegistroEntity newEntity = factory.manufacturePojo(RegistroEntity.class);

        newEntity.setId(entity.getId());

        registroPersistence.update(newEntity);

        RegistroEntity resp = em.find(RegistroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTipoRegistro(), resp.getTipoRegistro());
        Assert.assertEquals(newEntity.getCantidad(), resp.getCantidad());
    }
    
}
