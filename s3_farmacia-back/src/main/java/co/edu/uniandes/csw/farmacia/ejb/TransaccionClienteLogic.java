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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author RAMÓN
 */
@Stateless
public class TransaccionClienteLogic
{
    private static final Logger LOGGER = Logger.getLogger(TransaccionClienteLogic.class.getName());
    
     @Inject
    private TransaccionClientePersistence persistence;
     
     @Inject
    private ClientePersistence clientePersistence;
       
     
     
     
      public TransaccionClienteEntity createTransaccionCliente( Long clienteId, TransaccionClienteEntity trans) throws BusinessLogicException
      {
         LOGGER.log(Level.INFO, "Inicia proceso de consultar las transacciones asociados al cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        trans.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar las transacciones asociados al Cliente con id = {0}", clienteId);
        return persistence.create(trans);          
      }
      
      
    public List<TransaccionClienteEntity> getTransaccionesCliente(Long Id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las transacciones");
       ClienteEntity clienteEntity = clientePersistence.find(Id);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las transacciones");
        return clienteEntity.getTransaccionesCliente();
    }
    
    public TransaccionClienteEntity getTransaccionCliente(Long idCliente,Long transacionclienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la transaccion con id = {0}",transacionclienteId );
        return persistence.find(idCliente, transacionclienteId);
    }
    
    public TransaccionClienteEntity updateTransaccionCliente(Long ClienteId, TransaccionClienteEntity transaccionCliente)
    {
       LOGGER.log(Level.INFO, String.format("Inicia proceso de actualizar la transaccion con id = {0} del cliente con id = %d", ClienteId), transaccionCliente.getId());
        ClienteEntity entity = clientePersistence.find(ClienteId);
        transaccionCliente.setCliente(entity);
        persistence.update(transaccionCliente);
        LOGGER.log(Level.INFO, String.format("Termina proceso de actualizar la transaccion con id = {0} del cliente con id = %d", ClienteId), transaccionCliente.getId());
        return transaccionCliente;
    }
    
    
    public void deleteTransaccionCliente( Long clienteId,Long transaccionClienteId) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, String.format("Inicia proceso de borrar la transaccion con id = {0} del cliente con id = %d", clienteId), transaccionClienteId);
        TransaccionClienteEntity old = getTransaccionCliente(clienteId, transaccionClienteId);
        if (old == null) {
            throw new BusinessLogicException(String.format("la transaccion con id = %d no esta asociado a el cliente con id = %d",transaccionClienteId, clienteId));
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, String.format("Termina proceso de borrar la transaccion con id = {0} del cliente con id = %d", clienteId), transaccionClienteId);
   
    }
    
}
