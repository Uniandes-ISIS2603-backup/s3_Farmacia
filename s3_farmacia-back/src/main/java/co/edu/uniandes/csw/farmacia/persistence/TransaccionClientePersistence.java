/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author RAMÓN
 */
@Stateless
public class TransaccionClientePersistence
{
    private static final Logger LOGGER = Logger.getLogger(TransaccionClientePersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    public TransaccionClienteEntity create (TransaccionClienteEntity transaccionEntity)
    {
        LOGGER.log(Level.INFO, "Creando una nueva transaccion");    
        em.persist(transaccionEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una transaccion.");
        return transaccionEntity;
    }
    
    /**
     * Busca todos los registros que esten en la base de datos
     * @return 
     */
    public List<TransaccionClienteEntity> findAll(){
        //LOGGER.log(Level.INFO, "Consultando todos las transacciones.");
        
        //TypedQuery query = em.createQuery("Select u from TransaccionClienteEntity u", TransaccionClienteEntity.class);
       
        //return query.getResultList();
        LOGGER.log(Level.INFO, "Consultando todas las trnasaciconesCliente");
        Query q = em.createQuery("select u from TransaccionClienteEntity u");
        return q.getResultList();
    }
    
    /**
     * Busca un tranaccion entre todos los transacciones que esten en la base de datos
     * @param transaccionId
     * @return 
     */
    public TransaccionClienteEntity find (Long transaccionId){
        LOGGER.log(Level.INFO, "Consultando transacción con id={0}", transaccionId);
        return em.find(TransaccionClienteEntity.class, transaccionId);
    }
    
    /**
     * Actualiza una transacción
     * @param transaccionEntity
     * @return 
     */
    public TransaccionClienteEntity update(TransaccionClienteEntity transaccionEntity){
        LOGGER.log(Level.INFO, "Actualizando transaccion con id = {0}", transaccionEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la info de la transaccion con id = {0}",transaccionEntity.getId());
        return em.merge(transaccionEntity);
    }
    
    /**
     * Borra una transacción
     * @param transId 
     */
    public void delete(Long transId)
    {
        LOGGER.log(Level.INFO, "Borrando transaccion con id = {0}", transId);
        TransaccionClienteEntity entity = em.find(TransaccionClienteEntity.class, transId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de eliminar una transaccion con id = {0}", transId);
    }
    /**
    public TransaccionClienteEntity find(Long clienteId,Long transaccionId)
    {
        LOGGER.log(Level.INFO, "Consultando la transaccion con id = {0} del cliente con id = " + clienteId, transaccionId);
        TypedQuery<TransaccionClienteEntity> q = em.createQuery("select p from TransaccionClienteEntity p where (p.clinete.id = :clienteid) and (p.id = :transaccionClienteId)", TransaccionClienteEntity.class);
        q.setParameter("clineteid", clienteId);
        q.setParameter("trasnaccionClienteId", transaccionId);
        List<TransaccionClienteEntity> results = q.getResultList();
        TransaccionClienteEntity transaccion = null;
        if (results == null) {
            transaccion = null;
        } else if (results.isEmpty()) {
            transaccion = null;
        } else if (results.size() >= 1) {
            transaccion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el review con id = {0} del libro con id =" + clienteId, transaccionId);
        return transaccion;
    }
    */
}
