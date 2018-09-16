/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteClienteLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
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
public class TransaccionClienteClienteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TransaccionClienteLogic transaccionClienteLogic;
    @Inject
    private TransaccionClienteClienteLogic transaccionClienteClienteLogic;

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
                .addPackage(TransaccionClienteLogic.class.getPackage())
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
     * Prueba para remplazar las instancias de TransaccionesCliente asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceClienteTest() {
        TransaccionClienteEntity entity = transaccionesClienteData.get(0);
        transaccionClienteClienteLogic.replaceCliente(entity.getId(), data.get(1).getId());
        entity = transaccionClienteLogic.getTransaccionCliente(entity.getId());
        Assert.assertEquals(entity.getCliente(), data.get(1));
    }
    
    /**
     * Prueba para desasociar un Book existente de un Editorial existente
     *
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @Test
    public void removeTransasccionesClienteTest() throws BusinessLogicException {
        transaccionClienteClienteLogic.removeCliente(transaccionesClienteData.get(0).getId());
        TransaccionClienteEntity response = transaccionClienteLogic.getTransaccionCliente(transaccionesClienteData.get(0).getId());
        Assert.assertNull(response.getCliente());
    }
}
