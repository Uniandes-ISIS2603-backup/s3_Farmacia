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
 * @author estudiante
 */
@Stateless
public class ClienteTransaccionesClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteTransaccionesClienteLogic.class.getName());
    
    @Inject
    private TransaccionClientePersistence transaccionClientePersistence;
    
    @Inject
    private ClientePersistence clientePersistence;
    
    /**
     * Agregar un book a la editorial
     *
     * @param transaccionesClienteId El id transaccionCliente a guardar
     * @param clientesId El id del cliente en la cual se va a guardar el
     * transaccionCliente
     * @return La transaccionCliente creado.
     */
    public TransaccionClienteEntity addTransaccionCliente(Long transaccionesClienteId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una transaccion al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        TransaccionClienteEntity transaccionClienteEntity = transaccionClientePersistence.find(transaccionesClienteId);
        transaccionClienteEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una transaccion al cliente con id = {0}", clientesId);
        return transaccionClienteEntity;
    }
    
    /**
     * Retorna todas las trassaccionesCliente asociados a un cliente
     *
     * @param clientesId El ID del cliente buscado
     * @return La lista de transaccionesCliente de un cliente
     */
    public List<TransaccionClienteEntity> getTransaccionesCliente(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las trnasaccionesCliente asociados al cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getTransaccionesCliente();
    }
    
    /**
     * Retorna uan transaccionCliente asociado a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param transaccionesClienteId El id de la transaccionCliente a buscar
     * @return La transaccionCliente encontrado dentro del cliente.
     * @throws BusinessLogicException Si la transaccionCliente no se encuentra en la
     * cliente
     */
    public TransaccionClienteEntity getTransaccionCliente(Long clientesId, Long transaccionesClienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la transaccionCliente con id = {0} del cliente con id = " + clientesId, transaccionesClienteId);
        List<TransaccionClienteEntity> transacciones = clientePersistence.find(clientesId).getTransaccionesCliente();
        TransaccionClienteEntity transaccionEntity = transaccionClientePersistence.find(transaccionesClienteId);
        int index = transacciones.indexOf(transaccionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la transaccionCliente con id = {0} del cliente con id = " + clientesId, transaccionesClienteId);
        if (index >= 0) {
            return transacciones.get(index);
        }
        throw new BusinessLogicException("La transaccionCliente no está asociado a el cliente");
    }
    
    /**
     * Remplazar transaccionesCliente de un cliente
     *
     * @param transaccionesCliente Lista de transaccionesCliente que serán los del cliente.
     * @param clientesId El id del cliente que se quiere actualizar.
     * @return La lista de transaccionesCliente actualizada.
     */
    public List<TransaccionClienteEntity> replaceTransaccionesCliente(Long clientesId, List<TransaccionClienteEntity> transaccionesCliente) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        List<TransaccionClienteEntity> transaccionClienteList = transaccionClientePersistence.findAll();
        for (TransaccionClienteEntity transaccion : transaccionClienteList) {
            if (transaccionesCliente.contains(transaccion)) {
                transaccion.setCliente(clienteEntity);
            } else if (transaccion.getCliente() != null && transaccion.getCliente().equals(clienteEntity)) {
                transaccion.setCliente(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clientesId);
        return transaccionesCliente;
    }
    
}
