/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jd.florezg1
 */
@Stateless
public class TransaccionProveedorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionProveedorPersistence.class.getName());

    @PersistenceContext(unitName = "DrugsHousePU")
    protected EntityManager em;

    public TransaccionProveedorEntity create(TransaccionProveedorEntity transaccionProveedorEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo proveedor");

        em.persist(transaccionProveedorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo proveedor.");
        return transaccionProveedorEntity;
    }
    
    
    /**
     * Busca todos las TransaccionesProveedor que estén en la base de datos.
     *
     * @return
     */
    public List<TransaccionProveedorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los proveedores.");

        TypedQuery query = em.createQuery("select u from ProveedorEntity u", TransaccionProveedorEntity.class);

        return query.getResultList();
    }
    
    /**
     * Busca una transaccionProveedor en la base de datos.
     *
     * @param transaccionProveedorId
     * @return
     */
    public ProveedorEntity find(Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Consultando proveedor con id={0}", transaccionProveedorId);
        return em.find(ProveedorEntity.class, transaccionProveedorId);
    }
    
    /**
     * Actualiza la transaccionProveedor.
     *
     * @param transaccionProveedorEntity
     * @return
     */
    public TransaccionProveedorEntity update(TransaccionProveedorEntity transaccionProveedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando proveedor con id = {0}", transaccionProveedorEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar la info del proveedor con id = {0}", transaccionProveedorEntity.getId());

        return em.merge(transaccionProveedorEntity);
    }
    
    /**
     * Borra a una transaccionProveedor.
     *
     * @param transaccionProveedorId
     */
    public void delete(Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", transaccionProveedorId);
        TransaccionProveedorEntity entity = em.find(TransaccionProveedorEntity.class, transaccionProveedorId);

        em.remove(entity);

        LOGGER.log(Level.INFO, "Saliendo de eliminar un proveedor con id={0}", transaccionProveedorId);
    }
    
}
