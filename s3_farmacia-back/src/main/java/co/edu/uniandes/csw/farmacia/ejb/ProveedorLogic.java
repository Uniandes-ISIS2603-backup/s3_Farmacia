/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author francisco
 */
@Stateless
public class ProveedorLogic {

    //Hacer qye la variable apunte a algún objeto de la clase ProveedorPersistence.
    @Inject
    private ProveedorPersistence proveedorPersistence;

    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    //Buscamos validar tres principios básicos :    
    //1. Verificar que no existan dos proveedores con el mismo nombre.
    public ProveedorEntity createProveedor(ProveedorEntity provEntity) throws BusinessLogicException {
        //buscamos si ya existe.
        LOGGER.log(Level.INFO,"Inicia proceso de creación del proveedor.");
        
        if(proveedorPersistence.findByName(provEntity.getNombre()) != null)
        {
            throw new BusinessLogicException("Ya existe un proveedor con este nombre : \" "+ provEntity.getNombre()+ "\"");
        }
        proveedorPersistence.create(provEntity);
        LOGGER.log(Level.INFO , "Termina proceso de creación del proveedor.");

        return provEntity;
    }
    public List<ProveedorEntity> getProveedores()
    {
        LOGGER.log(Level.INFO,"Iniciamos proceso para obtener los proveedores.");
        
        List<ProveedorEntity> listaDeProveedores = proveedorPersistence.findAll();
        
        LOGGER.log(Level.INFO,"Terminamos proceso para obtener los proveedores.");
        
        return listaDeProveedores;
    }
    public ProveedorEntity getProveedor(Long proveedorId)
    {
       LOGGER.log(Level.INFO,"Iniciamos proceso para obtener el proveedor con id = {0}", proveedorId);
       
       ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
       
       if(proveedorEntity == null){
           LOGGER.log(Level.SEVERE, "El proveedor con id = {0} no existe.", proveedorId);
           
       }
       LOGGER.log(Level.INFO,"Terminamos el proceso para obtener el proveedor con id = {0}", proveedorId );
       
       
       return proveedorEntity;
    }
    public ProveedorEntity updateProveedor(Long proveedorId, ProveedorEntity provEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualización del proveedor con id={0} ", proveedorId);
        
        ProveedorEntity nuevaEntidadProveedor = proveedorPersistence.update(provEntity);
        
        LOGGER.log(Level.INFO, "Terminando de actualizar el proveedor con id={0}", proveedorId);
        return nuevaEntidadProveedor;
    }
    public void deleteProveedor(Long proveedorId)
    {
       LOGGER.log(Level.INFO, "Inicia proceso de eliminación del proveedor con id={0} ", proveedorId);
       
       proveedorPersistence.delete(proveedorId);
       
        LOGGER.log(Level.INFO, "Finaliza proceso de eliminación del proveedor con id={0} ", proveedorId);

    }

}
