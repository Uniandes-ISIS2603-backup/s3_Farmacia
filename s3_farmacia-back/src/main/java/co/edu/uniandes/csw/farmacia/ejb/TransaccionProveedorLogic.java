/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
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
    
    @Inject
    private ProveedorPersistence proveedorPersistence;
    /**
     * Crea una transaccion proveedor en la persistencia.
     *
     * @param transaccionProveedorEntity La entidad que representa la transaccion proveedor a
     * persistir.
     * @param proveedorId
     * @return La entiddad de la transaccionProveedor luego de persistirla.
     */
    public TransaccionProveedorEntity createTransaccionProveedor(TransaccionProveedorEntity transaccionProveedorEntity, Long proveedorId){
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la transaccion proveedor");
        persistence.create(transaccionProveedorEntity);
        ProveedorEntity proveedor = proveedorPersistence.find(proveedorId);
        transaccionProveedorEntity.setProveedor(proveedor);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la transaccion proveedor");
        return transaccionProveedorEntity;
    }

    /**
     *
     * Obtener todas las transacciones proveedor existentes en la base de datos.
     *
     * @param proveedorId
     * @return una lista de editoriales.
     */
    public List<TransaccionProveedorEntity> getTransaccionesProveedor(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las transacciones proveedor");
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las transacciones proveedor");
        return proveedorEntity.getTransacciones();
    }

    /**
     *
     * Obtener una transaccion proveedor por medio de su id.
     * @param proveedorId: id del proveedor que tiene la transacción proveedor.
     * @param transaccionProveedorId: id de la TransaccionProveedor para ser buscada.
     * @return la transaccion proveedor solicitada por medio de su id.
     */
    public TransaccionProveedorEntity getTransaccionProveedor(Long proveedorId,Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la transaccion con id = {0} del proveedor con id = " + proveedorId, transaccionProveedorId);
        return persistence.find(proveedorId, transaccionProveedorId);
    }

    /**
     *
     * Actualizar una transaccion proveedor.
     *
     * @param proveedorId: id del proveedor para buscarlo en la base de
     * datos.
     * @param transaccionProveedorEntity: transaccionProveedor con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la transaccionProveedor con los cambios actualizados en la base de datos.
     */
    public TransaccionProveedorEntity updateTransaccionProveedor(Long proveedorId, TransaccionProveedorEntity transaccionProveedorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la transaccionProveedor con id = {0} del proveedor con id = " + proveedorId, transaccionProveedorEntity.getId());
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        transaccionProveedorEntity.setProveedor(proveedorEntity);
        TransaccionProveedorEntity newEntity = persistence.update(transaccionProveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la transaccionProveedor con id = {0} del proveedor con id" + proveedorId, transaccionProveedorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar una transaccionProveedor
     *
     * @param proveedorId
     * @param transaccionProveedorId: id de la transaccionProveedor a borrar
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    public void deleteTransaccionProveedor(Long proveedorId, Long transaccionProveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la transaccionProveedor con id = {0} del proveedor con id" + proveedorId, transaccionProveedorId);
        TransaccionProveedorEntity old = getTransaccionProveedor(proveedorId, transaccionProveedorId);
        if (old == null) {
            throw new BusinessLogicException("la transaccion proveedor con id = " + transaccionProveedorId + " no esta asociado a el proveedor con id = " + proveedorId);
        }
        persistence.delete(transaccionProveedorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la transaccionProveedor con id = {0}", transaccionProveedorId);
    }
    
}
