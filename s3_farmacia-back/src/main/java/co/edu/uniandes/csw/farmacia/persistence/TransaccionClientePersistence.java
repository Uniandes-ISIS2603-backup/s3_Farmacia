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
      LOGGER.log(Level.INFO, "Creando una transaccion nuevo");
        em.persist(transaccionEntity);
        LOGGER.log(Level.INFO, "Transaccion creado");
        return transaccionEntity;
    }
       
    /**
     * Busca un transaccion entre todos los transacciones que esten en la base de datos
     * @param transaccionId
     * @param clienteId
     * @return 
     */
    public TransaccionClienteEntity find (Long clienteId,Long transaccionId){
        LOGGER.log(Level.INFO, "Consultando la transaccion con id = {0} del Cliente con id = " + clienteId, transaccionId);
        TypedQuery<TransaccionClienteEntity> q = em.createQuery("select p from TransaccionClienteEntity p where (p.cliente.id = :cliente_id) and (p.id = :id)", TransaccionClienteEntity.class);
        q.setParameter("cliente_id", clienteId);
        q.setParameter("id", transaccionId);
        List<TransaccionClienteEntity> results = q.getResultList();
        TransaccionClienteEntity transaccion;
        if (results == null) {	        
            transaccion = null;	           
        } else if (results.isEmpty()) {	        
            transaccion = null;	           
        } else {	         
            transaccion = results.get(0);	           
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la transaccion con id = {0} del cliente con id =" + clienteId, transaccionId);
        return transaccion;
    }
    
    /**
     * Actualiza una transacción
     * @param transaccionEntity
     * @return 
     */
    public TransaccionClienteEntity update(TransaccionClienteEntity transaccionEntity){
         LOGGER.log(Level.INFO, "Actualizando transaccion con id = {0}", transaccionEntity.getId());
        return em.merge(transaccionEntity);
    }
    
    /**
     * Borra una transacción
     * @param transId 
     */
    public void delete(Long transId)
    {
        LOGGER.log(Level.INFO, "Borrando review con id = {0}", transId);
        TransaccionClienteEntity reviewEntity = em.find(TransaccionClienteEntity.class, transId);
        em.remove(reviewEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El review con id = {0}", transId);
    }
   
}
