/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author RAMÓN
 */
public class TransaccionClienteLogic
{
    private static final Logger LOGGER = Logger.getLogger(TransaccionClienteLogic.class.getName());
    
     @Inject
    private TransaccionClientePersistence persistence;
     
     @Inject
    private ClientePersistence clientePersistence;
     
      public TransaccionClienteEntity createTransaccionCliente( Long clienteId ,TransaccionClienteEntity transaccionClienteEntity)
      {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una transaccion del cliente");
        ClienteEntity pCliente = clientePersistence.find(clienteId);
        transaccionClienteEntity.setCliente(pCliente);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return persistence.create(transaccionClienteEntity);
      }
    
      
      
    public List<TransaccionClienteEntity> getTransaccionesCliente(Long clienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al book con id = {0}", clienteId);
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al book con id = {0}", clienteId);
        return clienteEntity.getTransaccionesCliente();
    }
    
    public TransaccionClienteEntity getTransaccionCliente(Long clienteID,Long transacionclienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + clienteID, transacionclienteId);
        return persistence.find(clienteID, transacionclienteId);
    }
    
    public TransaccionClienteEntity updateTransaccionCliente(Long ClienteId, TransaccionClienteEntity transaccionCliente)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la transaccionCliente con id = {0} del Cliente con id = " + ClienteId, transaccionCliente.getId());
        ClienteEntity pCliente = clientePersistence.find(ClienteId);
        transaccionCliente.setCliente(pCliente);
        persistence.update(transaccionCliente);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la transaccion con id = {0} del cliente con id = " + ClienteId, transaccionCliente.getId());
        return transaccionCliente;
    }
    
    
    public void deleteTransaccionCliente(Long clienteId, Long transaccionClienteId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + clienteId, transaccionClienteId);
        TransaccionClienteEntity borrar= getTransaccionCliente(clienteId, transaccionClienteId);
        if(borrar==null)
        {
         throw new BusinessLogicException("La transaccion con id = " + transaccionClienteId + " no esta asociado a el cliente con id = " + clienteId);  
        }
        
        persistence.delete(borrar.getId());
         LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + clienteId, transaccionClienteId);
    }
    
}
