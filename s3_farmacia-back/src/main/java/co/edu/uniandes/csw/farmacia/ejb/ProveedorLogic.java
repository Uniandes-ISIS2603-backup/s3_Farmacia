/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fj.gonzalez
 */
@Stateless
public class ProveedorLogic {

    //Hacer que la variable apunte a algún objeto de la clase ProveedorPersistence.
    /**
     * Injeccion del proveedor desde la persistencia en la logica.
     */
    @Inject
    private ProveedorPersistence proveedorPersistence;
    /**
     * Constante que representa la conexion para llevar el registro respectivo de las transacciones logicas
     */
    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    //Buscamos validar tres principios básicos :    
    //1. Verificar que no existan dos proveedores con el mismo nombre.
    public ProveedorEntity createProveedor(ProveedorEntity provEntity) throws BusinessLogicException 
    {
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
    /**
     * Obtiene los proveedores
     * @return la lista de proveedores
     */
    public List<ProveedorEntity> getProveedores()
    {
        LOGGER.log(Level.INFO,"Iniciamos proceso para obtener los proveedores.");
        
        List<ProveedorEntity> listaDeProveedores = proveedorPersistence.findAll();
        
        LOGGER.log(Level.INFO,"Terminamos proceso para obtener los proveedores.");
        
        return listaDeProveedores;
    }
    /**
     * Obtiene un proveedor a partir de su id
     * @param proveedorId el id del proveedor a buscar
     * @return el proveedor
     */
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
    /**
     * Actualiza la informacion del proveedor identificado con el id especifico
     * @param proveedorId el id del proveedor a modificar
     * @param provEntity la nueva representacion del proveedor
     * @return el proveedor actualizado
     */
    public ProveedorEntity updateProveedor(Long proveedorId, ProveedorEntity provEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualización del proveedor con id={0} ", proveedorId);
        
        ProveedorEntity nuevaEntidadProveedor = proveedorPersistence.update(provEntity);
        
        LOGGER.log(Level.INFO, "Terminando de actualizar el proveedor con id={0}", proveedorId);
        return nuevaEntidadProveedor;
    }
    /**
     * Elimina un proveedor a partir de su id
     * @param proveedorId el id del proveedor a eliminar
     * @throws BusinessLogicException Si el proveedor que se desea eliminar no existe.
     */
    public void deleteProveedor(Long proveedorId) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO, "Inicia proceso de eliminación del proveedor con id={0} ", proveedorId);
      List<TransaccionProveedorEntity> lista = getProveedor(proveedorId).getTransacciones();
       if(lista != null && !lista.isEmpty())
       {
           throw new BusinessLogicException("No se puede borrar el proveedor con id " +proveedorId +"debido a que aún tiene transacciones asociadas.");
       }
       
       proveedorPersistence.delete(proveedorId);
       
        LOGGER.log(Level.INFO, "Finaliza proceso de eliminación del proveedor con id={0} ", proveedorId);

    }

}
