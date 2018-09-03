/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.test.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
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
 * @author francisco
 */
@RunWith(Arquillian.class)
public class ProveedorPersistenceTest 
{
    //el manejador manda un objeto de la persistencia
    @Inject
    private ProveedorPersistence proveedorPersistence;
    
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
   @Test
    public void createProveedorTest()
    {
        //Crea objetos aleatorios
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newProveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result = proveedorPersistence.create(newProveedorEntity);
        
        Assert.assertNotNull(result);        
        
    }
    
}
