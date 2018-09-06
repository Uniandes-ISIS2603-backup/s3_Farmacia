/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.florezg1
 */
@RunWith(Arquillian.class)
public class TransaccionProveedorPersistenceTest {
    
    @Inject
    private TransaccionProveedorPersistence transaccionProveedorPersistence;


  //  @Inject
   // UserTransaction utx;

   // private List<TransaccionProveedorEntity> data = new ArrayList<TransaccionProveedorEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionProveedorEntity.class.getPackage())
                .addPackage(TransaccionProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createTransaccionProveedorTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionProveedorEntity newTransaccionProveedorEntity = factory.manufacturePojo(TransaccionProveedorEntity.class);
        TransaccionProveedorEntity result = transaccionProveedorPersistence.create(newTransaccionProveedorEntity);       
        Assert.assertNotNull(result);        
        
    }
}
