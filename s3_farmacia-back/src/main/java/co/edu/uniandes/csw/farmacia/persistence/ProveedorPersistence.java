 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.List;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fj.gonzalez
 */
@Stateless
public class ProveedorPersistence {

    private static final Logger LOGGER = Logger.getLogger(ProveedorPersistence.class.getName());

        @PersistenceContext(unitName = "DrugsHousePU")
    protected EntityManager em;
        
        @Inject
    protected TransaccionProveedorPersistence tpp;

    public ProveedorEntity create(ProveedorEntity proveedorEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo proveedor");

        //Crea.
        em.persist(proveedorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo proveedor.");
        return proveedorEntity;
    }

    /**
     * Busca todos los proveedores que estÃ©n en la base de datos.
     *
     * @return
     */
    public List<ProveedorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los proveedores.");

        TypedQuery query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);

        return query.getResultList();
    }

    /**
     * Busca un proveedor entre todos los proveedores que estÃ©n en la base de
     * datos.
     *
     * @param proveedorId
     * @return
     */
    public ProveedorEntity find(Long proveedorId) {
        LOGGER.log(Level.INFO, "Consultando proveedor con id={0}", proveedorId);
        return em.find(ProveedorEntity.class, proveedorId);
    }

    /**
     * Actualiza a un proveedor.
     *
     * @param proveedorEntity
     * @return
     */
    public ProveedorEntity update(ProveedorEntity proveedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando proveedor con id = {0}", proveedorEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar la info del proveedor con id = {0}", proveedorEntity.getId());

        return em.merge(proveedorEntity);
    }

    /**
     * Borra a un proveedor.
     *
     * @param proveedorId
     */
    public void delete(Long proveedorId) {
        LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", proveedorId);
        ProveedorEntity entity = em.find(ProveedorEntity.class, proveedorId);

        em.remove(entity);

        LOGGER.log(Level.INFO, "Saliendo de eliminar un proveedor con id={0}", proveedorId);
    }
    
    public void delete2(Long proveedorId)
    {
        LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", proveedorId);
        ProveedorEntity entity = em.find(ProveedorEntity.class, proveedorId);
        if(entity.getTransacciones() != null)
        {
            for(int i = 0; i < entity.getTransacciones().size(); i++)
            {
               tpp.delete(entity.getTransacciones().get(i).getId());
            }
        }
        
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de eliminar un proveedor con id={0}", proveedorId);
    }
    
    /**
     * Busca si hay algÃºn proveedor con el nombre que se envÃ­a de parÃ¡metro
     *
     * @param name: Nombre del proveedor que se estÃ¡ buscando
     * @return null si no existe ningun proveedor con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public ProveedorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando proveedor por nombre {0}", name);
        TypedQuery query = em.createQuery("Select e From ProveedorEntity e where e.nombre = :nombre", ProveedorEntity.class);
        query = query.setParameter("nombre", name);
        List<ProveedorEntity> sameName = query.getResultList();
        ProveedorEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar proveedor por nombre {0}", name);
        return result;
    }

}
