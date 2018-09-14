/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.ClienteLogic;
import co.edu.uniandes.csw.farmacia.ejb.ClienteTransaccionesClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ClienteTransaccionesClienteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private ClienteTransaccionesClienteLogic clienteTransaccionesClienteLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
    
    private List<TransaccionClienteEntity> transaccionesClienteData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from TransaccionClienteEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TransaccionClienteEntity transacciones = factory.manufacturePojo(TransaccionClienteEntity.class);
            em.persist(transacciones);
            transaccionesClienteData.add(transacciones);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                transaccionesClienteData.get(i).setCliente(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar un transaciconesCliente existente a un Cliente
     */
    @Test
    public void addTransaccionesClienteTest() {
        ClienteEntity entity = data.get(0);
        TransaccionClienteEntity transaccionClienteEntity = transaccionesClienteData.get(1);
        TransaccionClienteEntity response = clienteTransaccionesClienteLogic.addTransaccionCliente(transaccionClienteEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(transaccionClienteEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de transaccionesCliente asociadas a un
     * instancia Cliente.
     */
    @Test
    public void getTransaccionesClienteTest() {
        List<TransaccionClienteEntity> list = clienteTransaccionesClienteLogic.getTransaccionesCliente(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
    /**
     * Prueba para obtener una instancia de transaccionesCliente asociada a una instancia
     * de Cliente
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void getTransaccionClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        TransaccionClienteEntity transaccionClienteEntity = transaccionesClienteData.get(0);
        TransaccionClienteEntity response = clienteTransaccionesClienteLogic.getTransaccionCliente(entity.getId(), transaccionClienteEntity.getId());

        Assert.assertEquals(transaccionClienteEntity.getId(), response.getId());
        Assert.assertEquals(transaccionClienteEntity.getMonto(), response.getMonto());
        Assert.assertEquals(transaccionClienteEntity.getTiempo(), response.getTiempo());
        Assert.assertEquals(transaccionClienteEntity.getTipoDePago(), response.getTipoDePago());
        
    }
    
    /**
     * Prueba para obtener una instancia de transaccionesCliente asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getTransaccionClienteNoAsociadoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        TransaccionClienteEntity transaccionEntity = transaccionesClienteData.get(1);
        clienteTransaccionesClienteLogic.getTransaccionCliente(entity.getId(), transaccionEntity.getId());
    }
    
    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Editorial.
     */
    @Test
    public void replaceTransaccionesClienteTest() {
        ClienteEntity entity = data.get(0);
        List<TransaccionClienteEntity> list = transaccionesClienteData.subList(1, 3);
        clienteTransaccionesClienteLogic.replaceTransaccionesCliente(entity.getId(), list);

        entity = clienteLogic.getCliente(entity.getId());
        Assert.assertFalse(entity.getTransaccionesCliente().contains(transaccionesClienteData.get(0)));
        Assert.assertTrue(entity.getTransaccionesCliente().contains(transaccionesClienteData.get(1)));
        Assert.assertTrue(entity.getTransaccionesCliente().contains(transaccionesClienteData.get(2)));
    }
}
