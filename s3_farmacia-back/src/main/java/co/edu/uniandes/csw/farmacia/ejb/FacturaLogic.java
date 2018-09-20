/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.FacturaPersistence;
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
public class FacturaLogic {
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
        
    public List<FacturaEntity> findfacturas(){
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
}
