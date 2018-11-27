/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
/**
 *
 * @author estudiante
 */
@Stateless
public class RegistroPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(RegistroPersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    /**
     * Crea un registro
     * Crea un nuevo regsitro con la informacion recibida en la entidad
     * @param registroEntity La entidad que representa el nuevo registro
     * @return La entidad creada
     */
    public RegistroEntity create (RegistroEntity registroEntity){
        LOGGER.log(Level.INFO, "Creando un nuevo registro");
        em.persist(registroEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un registro.");
        return registroEntity;
    }
    
    /**
     * Busca si hay algun registro asociado con un producto y con un ID especifico
     * @param registrosId el Id del registro buscado
     * @param productosId el Id del libro respecto al cual se busa
     * @return El registro encontrado o null. (Si existe un uno o mas registros 
     * devuelce siempre el primero que se encuentra)
     */
    public RegistroEntity find (Long productosId, Long registrosId){
        LOGGER.log(Level.INFO, "Consultando el registro con id = {0} del producto con id = " + productosId, registrosId);
        TypedQuery<RegistroEntity> q = em.createQuery("select p from RegistroEntity p where (p.producto.id = :productoid) and (p.id = :registrosId)", RegistroEntity.class);
        q.setParameter("productoid", productosId);
        q.setParameter("registrosId", registrosId);
        List<RegistroEntity> results = q.getResultList();
        RegistroEntity review;
        if (results == null) {	        
            review = null;	           
        } else if (results.isEmpty()) {	        
            review = null;	           
        } else {	         
            review = results.get(0);	           
        }
        LOGGER.log(Level.INFO, String.format("Saliendo de consultar el registro con id = {0} del producto con id = %d", productosId), registrosId);
        return review;
    }
    /**
     * 
     * @param productoId
     * @return 
     */
    public List<RegistroEntity> findRegistros(Long productoId)
    {
         LOGGER.log(Level.INFO, "Consultando el registro con id = {0} del producto con id = " + productoId, productoId);
        TypedQuery<RegistroEntity> q = em.createQuery("select p from RegistroEntity p where (p.producto.id = :productoid)", RegistroEntity.class);
         q.setParameter("productoid", productoId);
         List<RegistroEntity> results = q.getResultList();
         return results;

    }
    
      /**
     * Busca si hay algun registro asociado con un producto y con un ID especifico
     * @param registrosId el Id del registro buscado
     * @return El registro encontrado o null. (Si existe un uno o mas registros 
     * devuelce siempre el primero que se encuentra)
     */
    public RegistroEntity find (Long registrosId){
        TypedQuery<RegistroEntity> q = em.createQuery("select p from RegistroEntity p where (p.id = :registrosId)", RegistroEntity.class);
        q.setParameter("registrosId", registrosId);
        List<RegistroEntity> results = q.getResultList();
        RegistroEntity review = null;
        if (results == null) {
            
        } else if (results.isEmpty()) {
            
        } else if (results.size() >= 1) {
            review = results.get(0);
        }
        LOGGER.log(Level.INFO, String.format("Saliendo de consultar el registro con id = %d", registrosId));
        return review;
    }
    
    /**
     * Actualiza un registro
     * @param registroEntity La entidad actualizada que se desea guardar
     * @return la entidad resultante luego de la actualizacion
     */
    public RegistroEntity update(RegistroEntity registroEntity){
        LOGGER.log(Level.INFO, "Actualizando registro con id = {0}", registroEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la info del registro con id = {0}",registroEntity.getId());
        return em.merge(registroEntity);
    }
    
    /**
     * Borra un registro
     * @param registroId 
     */
    public void delete(Long registroId){
        LOGGER.log(Level.INFO, "Borrando registro con id = {0}", registroId);
        RegistroEntity entity = em.find(RegistroEntity.class, registroId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de eliminar un registro con id = {0}", registroId);
    }
    
    public List<RegistroEntity> list() {
        return em
                .createQuery("select e from RegistroEntity e",
                        RegistroEntity.class)
                .getResultList();
    }
    
    
}
