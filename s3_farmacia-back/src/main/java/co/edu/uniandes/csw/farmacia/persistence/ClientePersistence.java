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
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author estudiante
 */
@Stateless
public class ClientePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    @Inject
    protected TransaccionClientePersistence tcp;
    
    /**
     * Metodo para persisitir la entidad en la base de datos
     * @param clienteEntity objeto cliente que se creara en la base de datos
     * @return devuelve la entiudad creada con un id dado por la base de datos.
     */
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
        LOGGER.log(Level.INFO, "Saliendo de actualizar la info del cliente con id = {0}", clienteEntity.getId());
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
    
    public void delete2(Long clienteId)
    {
        LOGGER.log(Level.INFO, "Borrando cliente con id ={0}", clienteId);
        
              Query query =  em.createQuery("delete from TransaccionClienteEntity where cliente_id = :id");
              query.setParameter("id", clienteId);
              query.executeUpdate();
              
              ClienteEntity cli = em.find(ClienteEntity.class, clienteId);
              
              em.remove(cli);
              
        
        LOGGER.log(Level.INFO, "Saliendo de eliminar a un cliente con id={0}", clienteId);
    }
    
    /**
     * Encuentra un cliente por medio de la cedula, esta numero de cedula
     * es unico
     * @param cedula el numero de cedula del cliente que se quiere encontrar
     * @return 
     */
    public ClienteEntity findByCedula(Long cedula){
        LOGGER.log(Level.INFO, "Consultando cliente por cedula {0}", cedula);
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.cedula = :cedula", ClienteEntity.class);
        query = query.setParameter("cedula", cedula);
        List<ClienteEntity> sameCedula = query.getResultList();
        ClienteEntity result;
        if(sameCedula == null){
            result = null;
        }else if (sameCedula.isEmpty()){
            result = null;
        }else{
            result = sameCedula.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar un cliente por nombre {0}",cedula );
        return result;
    }
    
    
}
