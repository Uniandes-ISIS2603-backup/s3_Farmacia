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
import javax.ejb.Stateless;
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
    
    public RegistroEntity create (RegistroEntity registroEntity){
        LOGGER.log(Level.INFO, "Creando un nuevo registro");
        
        em.persist(registroEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un registro.");
        return registroEntity;
    }
    
    /**
     * Busca todos los registros que esten en la base de datos
     * @return 
     */
    public List<RegistroEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todos los registros.");
        
        TypedQuery query = em.createQuery("Select u from ClienteEntity u", RegistroEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca un registro entre todos los registros que esten en la base de datos
     * @param registroId
     * @return 
     */
    public RegistroEntity find (Long registroId){
        LOGGER.log(Level.INFO, "Consultando registro con id={0}", registroId);
        return em.find(RegistroEntity.class, registroId);
    }
    
    /**
     * Actualiza un registro
     * @param registroEntity
     * @return 
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
        LOGGER.log(Level.INFO, "Saliendo de eliminar un proveedor con id = {0}", registroId);
    }
    
    /**
     * Busca si hay un registro con el nombre que se envia de argumento
     * 
     * @param name Nombre del registro que se esta buscando
     * @return null si no existe ninguna editorial con el nombre del argumento
     * Si existe alguno devuelve el primero
     
    public RegistroEntity findByName(String name){
        LOGGER.log(Level.INFO, "Consultando registro por nombre", name);
        TypedQuery query = em.createQuery("Select e From RegistroEntity e where e.name = :name", RegistroEntity.class);
        query = query.setParameter("name", name);
        List<RegistroEntity> sameName = query.getResultList();
        RegistroEntity result;
        if(sameName == null){
            result = null;
        }else if (sameName.isEmpty()){
            result = null;
        }else{
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar registro por nombre", name);
        return result;
    }
    */
}
