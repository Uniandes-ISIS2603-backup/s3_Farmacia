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
     
      public TransaccionClienteEntity createTransaccionCliente( TransaccionClienteEntity transaccionClienteEntity) throws BusinessLogicException
      {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una transaccion del cliente");
                 
       if(transaccionClienteEntity.getCliente()==null )
          {
           throw new BusinessLogicException("El cliente es invalido"); 
          }
 
       persistence.create(transaccionClienteEntity);
       LOGGER.log(Level.INFO,"Termina proceso de creacion de la transaccion");
        return transaccionClienteEntity;
      }
      
      
    public List<TransaccionClienteEntity> getTransaccionesCliente()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las transacciones");
       List<TransaccionClienteEntity> clienteEntity = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las transacciones");
        return clienteEntity;
    }
    
    public TransaccionClienteEntity getTransaccionCliente(Long transacionclienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la transaccion con id = {0}",transacionclienteId );
        TransaccionClienteEntity transaccion= persistence.find(transacionclienteId);
        if(transaccion==null)
        {
            LOGGER.log(Level.SEVERE,"La transaccion con el id={0} no existe", transacionclienteId);
        }
        LOGGER.log(Level.INFO,"Termina el proceso de consulta de la transaccion con id = {0}",transacionclienteId);
        return transaccion;
    }
    
    public TransaccionClienteEntity updateTransaccionCliente(Long transaccionClienteId, TransaccionClienteEntity transaccionCliente)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la transaccionCliente con id = {0} " , transaccionClienteId);
        TransaccionClienteEntity newEntity = persistence.update(transaccionCliente);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar una transaccion con id = {0}", transaccionCliente.getId());
        return newEntity;
    }
    
    
    public void deleteTransaccionCliente( Long transaccionClienteId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la transaccion con id = {0}", transaccionClienteId);
        persistence.delete(transaccionClienteId);
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la transaccion con id = {0}", transaccionClienteId);
   
    }
    
}
