/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author estudiante
 */
@Stateless
public class ClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private ClientePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea una Cliente en la persistencia.
     *
     * @param clienteEntity La entidad que representa lel cliente a
     * persistir.
     * @return La entiddad de la cliente luego de persistirla.
     * @throws BusinessLogicException Si el cliente a persistir ya existe.
     */
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la cliente");
        // Verifica la regla de negocio que dice que no puede haber dos clientes con el mismo nombre
        if(!validateCedula(clienteEntity.getCedula())){
            throw new BusinessLogicException("La cedula es invalida");
        }
        if (persistence.findByCedula(clienteEntity.getCedula()) != null) {
            throw new BusinessLogicException("Ya existe una Cliente con la cedula \"" + clienteEntity.getCedula() + "\"");
        }
        // Invoca la persistencia para crear el cliente
        persistence.create(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return clienteEntity;
    }
    
    /**
     * Obtener todos los clientes de la base de datos
     *
     * @return una lista de clientes.
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ClienteEntity> editorials = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes");
        return editorials;
    }
    
     /**
     * Obtener un cliente por medio de su id
     *
     * @param clienteId: id del cliente para ser buscada.
     * @return el cliente solicitado por medio de su id.
     */
    public ClienteEntity getCliente(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0}", clienteId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) {
            LOGGER.log(Level.SEVERE, "El cliente con el id = {0} no existe", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0}", clienteId);
        return clienteEntity;
    }
    
    /**
     * Actualizar un cliente.
     *
     * @param clienteId: id del cliente para buscarla en la base de
     * datos.
     * @param clienteEntity: cliente con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return el cliente con los cambios actualizados en la base de datos.
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity clienteEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clienteId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        if(!validateCedula(clienteEntity.getCedula())){
            throw new BusinessLogicException("La cedula es inválida");
        }
        ClienteEntity newEntity = persistence.update(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clienteEntity.getId());
        return newEntity;
    }
    
    /**
     * Borrar un cliente
     *
     * @param clienteId: id del cliente a borrar
     */
    public void deleteCliente(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clienteId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        
        persistence.delete(clienteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", clienteId);
    }
    
    /**
     * valida el numero de cedula de un cliente.
     * Para que una cedula sea valida debe tener estrictamente 10 digitos.
     * @param cedula el numero de cedula que se quiere validar
     * @return true si es valida, false de lo contrario
     */
    private boolean validateCedula(Long cedula){
        return (cedula>=1000000000L && cedula<=9999999999L);
    }
}
