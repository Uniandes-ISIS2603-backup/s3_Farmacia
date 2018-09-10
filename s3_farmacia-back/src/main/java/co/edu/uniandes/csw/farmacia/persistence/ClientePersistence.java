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
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import javax.ejb.Stateless;

/**
 *
 * @author estudiante
 */
@Stateless
public class ClientePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    public ClienteEntity create(ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Creando un nuevo cliente");
        
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo cliente.");
        return clienteEntity;   
    }
    
    /**
     * Busca todos los clientes que esten en la base de datos
     * @return 
     */
    public List<ClienteEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todos los clientes.");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }
    /**
     * Busca un cliente entre todos los clientes que esten
     * en la base de datos
     * @param clienteId
     * @return 
     */
    public ClienteEntity find(Long clienteId){
        LOGGER.log(Level.INFO,"Consultando cliente con id={0}", clienteId);
        return em.find(ClienteEntity.class, clienteId);
    }
    
    /**
     * Actualiza a un cliente
     * @param clienteEntity
     * @return 
     */
    public ClienteEntity update (ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Actualizando cliente con id = {0}", clienteEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la info del proveedor con id = {0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }
    /**
     * Elimina a un determiando cliente con un determiando
     * id
     * @param clienteId 
     */
    public void delete(Long clienteId){
        LOGGER.log(Level.INFO, "Borrando cliente con id ={0}", clienteId);
       ClienteEntity entity = em.find(ClienteEntity.class, clienteId);
       em.remove(entity);
       LOGGER.log(Level.INFO, "Saliendo de eliminar a un cliente con id={0}", clienteId);
    }
    
    public ClienteEntity findByName(String name){
        LOGGER.log(Level.INFO, "Consultando cliente por nombre ", name);
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.nombre = :nombre", ClienteEntity.class);
        query = query.setParameter("nombre", name);
        List<ClienteEntity> sameName = query.getResultList();
        ClienteEntity result;
        if(sameName == null){
            result = null;
        }else if (sameName.isEmpty()){
            result = null;
        }else{
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar un cliente por nombre", name);
        return result;
    }
    
}