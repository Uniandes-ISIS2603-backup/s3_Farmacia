/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author jd.florezg1
 */
public class TransaccionProveedorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionProveedorLogic.class.getName());

    @Inject
    private TransaccionProveedorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una transaccion proveedor en la persistencia.
     *
     * @param transaccionProveedorEntity La entidad que representa la transaccion proveedor a
     * persistir.
     * @return La entiddad de la transaccionProveedor luego de persistirla.
     */
    public TransaccionProveedorEntity createTransaccionProveedor(TransaccionProveedorEntity transaccionProveedorEntity){
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la transaccion proveedor");
        persistence.create(transaccionProveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la transacicon proveedor");
        return transaccionProveedorEntity;
    }

    /**
     *
     * Obtener todas las transacciones proveedor existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<TransaccionProveedorEntity> getTransaccionesProveedor() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las transacciones proveedor");
        List<TransaccionProveedorEntity> transaccionesProveedor = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las transacciones proveedor");
        return transaccionesProveedor;
    }

    /**
     *
     * Obtener una transaccion proveedor por medio de su id.
     *
     * @param transaccionProveedorId: id de la TransaccionProveedor para ser buscada.
     * @return la transaccion proveedor solicitada por medio de su id.
     */
    public TransaccionProveedorEntity getTransaccionProveedor(Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la transaccionProveedor con id = {0}", transaccionProveedorId);
        TransaccionProveedorEntity transaccionProveedorEntity = persistence.find(transaccionProveedorId);
        if (transaccionProveedorEntity == null) {
            LOGGER.log(Level.SEVERE, "La transaccionProveedor con el id = {0} no existe", transaccionProveedorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la transaccionProveedor con id = {0}", transaccionProveedorId);
        return transaccionProveedorEntity;
    }

    /**
     *
     * Actualizar una transaccion proveedor.
     *
     * @param transaccionProveedorId: id de la transaccionProveedor para buscarla en la base de
     * datos.
     * @param transaccionProveedorEntity: transaccionProveedor con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la transaccionProveedor con los cambios actualizados en la base de datos.
     */
    public TransaccionProveedorEntity updateTransaccionProveedor(Long transaccionProveedorId, TransaccionProveedorEntity transaccionProveedorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la transaccionProveedor con id = {0}", transaccionProveedorId);
        TransaccionProveedorEntity newEntity = persistence.update(transaccionProveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la transaccionProveedor con id = {0}", transaccionProveedorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar una transaccionProveedor
     *
     * @param transaccionProveedorId: id de la transaccionProveedor a borrar
     */
    public void deleteEditorial(Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la transaccionProveedor con id = {0}", transaccionProveedorId);
        persistence.delete(transaccionProveedorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la transaccionProveedor con id = {0}", transaccionProveedorId);
    }
    
}
