/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.logic;

import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
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
 * @author RAMÓN
 */
@RunWith(Arquillian.class)
public class TransaccionClienteLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TransaccionClienteLogic TransaccionClienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TransaccionClienteEntity> data = new ArrayList<TransaccionClienteEntity>();

    private List<ClienteEntity> dataCliente = new ArrayList<ClienteEntity>();

@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionClienteEntity.class.getPackage())
                .addPackage(TransaccionClienteLogic.class.getPackage())
                .addPackage(TransaccionClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() 
    {
        em.createQuery("delete from TransaccionClienteEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    private void insertData() 
    {
        for(int i = 0 ; i<3;i++)
        {
            ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
            em.persist(cliente);
            dataCliente.add(cliente);
          
        }
        for(int j=0; j<3;j++)
        {
            TransaccionClienteEntity transaccion = factory.manufacturePojo(TransaccionClienteEntity.class);
            transaccion.setCliente(dataCliente.get(1));
           
            em.persist(transaccion);
            data.add(transaccion);
        }
        
    }
    @Test
    public void createTransaccionClienteTest() throws BusinessLogicException
    {
        TransaccionClienteEntity newEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
        newEntity.setCliente(dataCliente.get(1));
        TransaccionClienteEntity result = TransaccionClienteLogic.createTransaccionCliente(dataCliente.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        TransaccionClienteEntity entity = em.find(TransaccionClienteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMonto(), entity.getMonto());
        Assert.assertEquals(newEntity.getTiempo(), entity.getTiempo());
        Assert.assertEquals(newEntity.getTipoDePago(), entity.getTipoDePago());
        
    }
    
    @Test
    public void geTransaccionesTest() 
    {
        List<TransaccionClienteEntity> list = TransaccionClienteLogic.getTransaccionesCliente(dataCliente.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (TransaccionClienteEntity entity : list) {
            boolean found = false;
            for (TransaccionClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getTransaccionClienteTest() {
        TransaccionClienteEntity entity = data.get(0);
        TransaccionClienteEntity resultEntity = TransaccionClienteLogic.getTransaccionCliente(dataCliente.get(1).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getMonto(), resultEntity.getMonto());
        Assert.assertEquals(entity.getTiempo(), resultEntity.getTiempo());
        Assert.assertEquals(entity.getTipoDePago(), resultEntity.getTipoDePago());
        Assert.assertEquals(entity.getParcial(), resultEntity.getParcial());
        
    }

    @Test
    public void updateTransaccionTest() 
    {
        TransaccionClienteEntity entity = data.get(0);
        TransaccionClienteEntity pojoEntity = factory.manufacturePojo(TransaccionClienteEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        TransaccionClienteLogic.updateTransaccionCliente(dataCliente.get(1).getId(), pojoEntity);
       
        TransaccionClienteEntity resp = em.find(TransaccionClienteEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getMonto(), resp.getMonto());
        Assert.assertEquals(pojoEntity.getParcial(), resp.getParcial());
        Assert.assertEquals(pojoEntity.getTiempo(), resp.getTiempo());
        Assert.assertEquals(pojoEntity.getTipoDePago(), resp.getTipoDePago());
    }

   @Test
    public void deleteTransaccionTest() throws BusinessLogicException
    {
        TransaccionClienteEntity entity = data.get(0);
        TransaccionClienteLogic.deleteTransaccionCliente(dataCliente.get(1).getId(),entity.getId());
        TransaccionClienteEntity deleted = em.find(TransaccionClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    


}
