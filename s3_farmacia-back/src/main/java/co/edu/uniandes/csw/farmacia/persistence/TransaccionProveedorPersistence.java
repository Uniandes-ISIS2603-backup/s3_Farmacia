/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;
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
        LOGGER.log(Level.INFO, "Creando una nueva TransaccionProveedor");

        em.persist(transaccionProveedorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva TransaccionProveedor.");
        return transaccionProveedorEntity;
    }
    
    
    /**
     * Busca todos las TransaccionesProveedor que estén en la base de datos.
     *
     * @return
     */
    public List<TransaccionProveedorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las TransaccionesProveedor.");

        TypedQuery query = em.createQuery("select u from TransaccionProveedorEntity u", TransaccionProveedorEntity.class);

        return query.getResultList();
    }
    
    /**
     * Buscar una reseña
     *
     * Busca si hay alguna reseña asociada a un libro y con un ID específico
     *
     * @param proveedorId El ID del proveedor con respecto al cual se busca
     * @param transaccionProveedorId El ID de la transaccion proveedor buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas
     * devuelve siempre la primera que encuentra
     */
    public TransaccionProveedorEntity find(Long proveedorId, Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Consultando la transaccionProveedor con id = {0} del proveedor con id = " + proveedorId, transaccionProveedorId);
        TypedQuery<TransaccionProveedorEntity> q = em.createQuery("select p from TransaccionProveedorEntity p where (p.proveedor.id = :proveedorid) and (p.id = :transaccionProveedorId)", TransaccionProveedorEntity.class);
        q.setParameter("proveedorId", proveedorId);
        q.setParameter("transaccionProveedorId", transaccionProveedorId);
        List<TransaccionProveedorEntity> results = q.getResultList();
        TransaccionProveedorEntity transaccionProveedor = null;
        if (results == null) {
            transaccionProveedor = null;
        } else if (results.isEmpty()) {
            transaccionProveedor = null;
        } else if (results.size() >= 1) {
            transaccionProveedor = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la transaccionProveedor con id = {0} del proveedor con id =" + proveedorId, transaccionProveedorId);
        return transaccionProveedor;
    }
    
    /**
     * Actualiza la transaccionProveedor.
     *
     * @param transaccionProveedorEntity
     * @return
     */
    public TransaccionProveedorEntity update(TransaccionProveedorEntity transaccionProveedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando TransaccionProveedor con id = {0}", transaccionProveedorEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar la info de la TransaccionProveedor con id = {0}", transaccionProveedorEntity.getId());

        return em.merge(transaccionProveedorEntity);
    }
    
    /**
     * Borra a una transaccionProveedor.
     *
     * @param transaccionProveedorId
     */
    public void delete(Long transaccionProveedorId) {
        LOGGER.log(Level.INFO, "Borrando TransaccionProveedor con id={0}", transaccionProveedorId);
        TransaccionProveedorEntity entity = em.find(TransaccionProveedorEntity.class, transaccionProveedorId);

        em.remove(entity);

        LOGGER.log(Level.INFO, "Saliendo de eliminar una TransaccionProveedor con id={0}", transaccionProveedorId);
    }
    
}
