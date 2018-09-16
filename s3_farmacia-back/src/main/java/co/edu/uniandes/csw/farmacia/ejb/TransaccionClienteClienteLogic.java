/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TransaccionClienteClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionClienteClienteLogic.class.getName());
    
    @Inject
    private TransaccionClientePersistence transaccionClientePersistence;
    
    @Inject
    private ClientePersistence clientePersistence;
    
    /**
     * Remplazar el cliente de una transaccionCLiente.
     *
     * @param transaccionesClienteId id de la transaccionCLiete que se quiere actualizar.
     * @param clientesId El id del cliente que se será de la transaccionCliente.
     * @return la nueva transaccionCliente.
     */
    public TransaccionClienteEntity replaceCliente(Long transaccionesClienteId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar transaccionCliente con id = {0}", transaccionesClienteId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        TransaccionClienteEntity transaccionEntity = transaccionClientePersistence.find(transaccionesClienteId);
        transaccionEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar transaccionCliente con id = {0}", transaccionEntity.getId());
        return transaccionEntity;
    }
    
    /**
     * Borrar una transaccionCLiente de un cliente. Este metodo se utiliza para borrar la
     * relacion de una transaccionCLiente.
     *
     * @param transaccionesClienteId La transaccionCliente que se desea borrar del cliente.
     */
    public void removeCliente(Long transaccionesClienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Cliente de la transaccionCliente con id = {0}", transaccionesClienteId);
        TransaccionClienteEntity transaccionEntity = transaccionClientePersistence.find(transaccionesClienteId);
        ClienteEntity clienteEntity = clientePersistence.find(transaccionEntity.getCliente().getId());
        transaccionEntity.setCliente(null);
        clienteEntity.getTransaccionesCliente().remove(transaccionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Cliente de la transaccionCliente con id = {0}", transaccionEntity.getId());
    }
}
