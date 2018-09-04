/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class FacturaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorPersistence.class.getName());
    
   
    @PersistenceContext(unitName = "DrugsHousePU")
    protected EntityManager em;
    
    public FacturaEntity create(FacturaEntity entity){
        
        LOGGER.log(Level.INFO, "Creando una nueva factura");
        em.persist(entity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva factura");
        
        return entity;
      
    }
    
        public List<FacturaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los proveedores.");

        TypedQuery query = em.createQuery("select u from ProveedorEntity u", FacturaEntity.class);

        return query.getResultList(); 
    }
        
        public FacturaEntity find(Long facturaId) {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", facturaId);
        return em.find(FacturaEntity.class, facturaId);
    }

        
        public FacturaEntity update(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando factura con id = {0}", entity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar la info de la factura con id = {0}", entity.getId());

        return em.merge(entity);
    }
        
        public void delete(Long facturaId) {
        LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", facturaId);
        FacturaEntity entity = em.find(FacturaEntity.class, facturaId);

        em.remove(entity);

        LOGGER.log(Level.INFO, "Saliendo de eliminar un proveedor con id={0}", facturaId);
    }
        

    

}