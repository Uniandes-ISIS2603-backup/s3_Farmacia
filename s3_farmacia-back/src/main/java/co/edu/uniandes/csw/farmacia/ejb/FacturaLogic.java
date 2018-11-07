/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.FacturaPersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.machado10
 */
@Stateless
public class FacturaLogic 
{
   

    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    @Inject
    private FacturaPersistence facturaPersistence;

    @Inject
    private TransaccionClientePersistence transaccionClientePersistence;
    
    

    public FacturaEntity createFactura(FacturaEntity facturaEntity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de premio");
        if (facturaEntity.getTransaccionCliente() == null) {
            throw new BusinessLogicException("La factura no está asociada a una transacción.");
        }
        TransaccionClienteEntity trEntity = transaccionClientePersistence.find(facturaEntity.getTransaccionCliente().getCliente().getId(),facturaEntity.getTransaccionCliente().getId());
        if (trEntity == null) {
            throw new BusinessLogicException("La transacción es inválida");
        }
        if (trEntity.getFactura() != null) {
            throw new BusinessLogicException("La transaccion ya tiene factura asociada.");
        }
        facturaEntity.setTransaccionCliente(trEntity);
        trEntity.setFactura(facturaEntity);
        facturaEntity = facturaPersistence.create(facturaEntity);
        LOGGER.info("Termina proceso de creación de factura");
        return facturaEntity;
    }

    /**
     * Devuelve todas las facturas  que hay en la base de datos.
     *
     * @return Lista de entidades de tipo factura.
     */
    public List<FacturaEntity> getFacturas() {
       LOGGER.info("Inicia proceso de consultar todas los facturas");
        List<FacturaEntity> facturas = facturaPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los facturas");
        return facturas;
    }
    public FacturaEntity getFactura(Long facturasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar premio con id = {0}", facturasId);
        FacturaEntity factura = facturaPersistence.find(facturasId);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "El premio con el id = {0} no existe", facturasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar premio con id = {0}", facturasId);
        return factura;
    }
    
        public FacturaEntity updateFactura(Long prizesId, FacturaEntity facturaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar premio con id = {0}", prizesId);
        FacturaEntity newEntity = facturaPersistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar premio con id = {0}", facturaEntity.getId());
        return newEntity;
    }
        
            public void deleteFactura(Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar premio con id = {0}", facturasId);
        if (facturaPersistence.find(facturasId).getTransaccionCliente() != null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + facturasId + " porque tiene una transaccion asociada");
        }
        facturaPersistence.delete(facturasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", facturasId);
    }


    /**
     * Busca un premio por ID
     *
     * @param prizesId El id del premio a buscar
     * @return El premio encontrado, null si no lo encuentra.
     */
  //  public PrizeEntity getPrize(Long prizesId) {
  //      LOGGER.log(Level.INFO, "Inicia proceso de consultar premio con id = {0}", prizesId);
 //       PrizeEntity prize = prizePersistence.find(prizesId);
  //      if (prize == null) {
    //        LOGGER.log(Level.SEVERE, "El premio con el id = {0} no existe", prizesId);
   //     }
   //     LOGGER.log(Level.INFO, "Termina proceso de consultar premio con id = {0}", prizesId);
   //     return prize;
   // }

    /**
     * Actualizar un premio por ID
     *
     * @param prizesId El ID del premio a actualizar
     * @param prizeEntity La entidad del premio con los cambios deseados
     * @return La entidad del premio luego de actualizarla
     */
//    public PrizeEntity updatePrize(Long prizesId, PrizeEntity prizeEntity) {
  //      LOGGER.log(Level.INFO, "Inicia proceso de actualizar premio con id = {0}", prizesId);
    //    PrizeEntity newEntity = prizePersistence.update(prizeEntity);
      //  LOGGER.log(Level.INFO, "Termina proceso de actualizar premio con id = {0}", prizeEntity.getId());
      //  return newEntity;
   // }

    /**
     * Eliminar un premio por ID
     *
     * @param facturaId El ID de la factura a eliminar
     * @throws BusinessLogicException si el premio tiene un autor asociado.
     */
   // public void deleteFactura(Long facturaId) throws BusinessLogicException {
     //   LOGGER.log(Level.INFO, "Inicia proceso de borrar factura con id = {0}", facturaId);
      //  if (prizePersistence.find(facturaId).getAuthor() != null) {
       //     throw new BusinessLogicException("No se puede borrar el premio con id = " + facturaId + " porque tiene un autor asociado");
       // }
       // prizePersistence.delete(facturaId);
       // LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", facturaId);
    //}

}

    /**
    @Inject
    private FacturaPersistence facturaPersistence;

    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());
    
    public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la factura");

        if (facturaPersistence.find(entity.getId()) != null) {
        throw new BusinessLogicException("Ya existe una Factura con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la factura
        facturaPersistence.create(entity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la Fact8ra");
        return entity;
    }
        
    public List<FacturaEntity> findFacturas(){
        return facturaPersistence.findAll();
    }
        
    public FacturaEntity getFactura(Long facturaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la factura con id = {0}", facturaId);
       
        FacturaEntity FacturaEntity = facturaPersistence.find(facturaId);
        if (FacturaEntity == null) {
            LOGGER.log(Level.SEVERE, "la factura con el id = {0} no existe", facturaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la factura con id = {0}", facturaId);
        return FacturaEntity;
    }    
        
        
    public FacturaEntity updateFactura(Long facturaId, FacturaEntity facturaEntity)throws BusinessLogicException {
            
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la factura con id = {0}", facturaId);
      
        FacturaEntity newEntity = facturaPersistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la con id = {0}", facturaEntity.getId());
        return newEntity;
    }
        
    
    public void deleteFactura(Long facturaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", facturaId);
        
        facturaPersistence.delete(facturaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", facturaId);
    }

**/
