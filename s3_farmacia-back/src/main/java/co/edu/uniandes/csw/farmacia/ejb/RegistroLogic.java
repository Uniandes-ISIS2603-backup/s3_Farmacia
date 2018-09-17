/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author estudiante
 */
@Stateless
public class RegistroLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RegistroLogic.class.getName());

    @Inject
    private RegistroPersistence persistence;

    @Inject
    private ProductoPersistence productoPersistence;
    
    
     
     /**
      * Se encargar de crear un Registro en la base de datos
      * @param productosId el id ek cyal sera padre del nuevo Registro
      * @param registroEntity objeto de RegistroEntity con los datos nuevos
      * @return
      * @throws BusinessLogicException 
      */
    public RegistroEntity createRegistro(Long productosId, RegistroEntity registroEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del registro");
        if (registroEntity.getTipoRegistro()== null) {
            throw new BusinessLogicException("El registro es inválido");
        }
        if (!validateRegistro(registroEntity.getTipoRegistro())) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        ProductoEntity producto = productoPersistence.find(productosId);
        registroEntity.setProducto(producto);
        LOGGER.log(Level.INFO, "Termina proceso de creación del registro");
        return persistence.create(registroEntity);
    }
     
    
    /**
     *obtiene la lista de los registros de Registro que pertecen a un Producto 
     * @param productosId el id del producto el cual es padre de los registros
     * @return Coleccion de objetos RegistroEntity
     */
    public List<RegistroEntity> getRegistros(Long productosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los registros");
        ProductoEntity productoEntity = productoPersistence.find(productosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los registros");
        return productoEntity.getRegistros();
    }
    
    /**
     * Obtiene los datos de una instancia de Registro a partir de su ID. la
     * existencia del elemento padre Producto se debe garantizar
     * @param productosId El id del producto buscado
     * @param registrosId Identificador del Registro a cosultar
     * @return Instancia de RegistroEntity con los datos del Registro consultado.
     */
    public RegistroEntity getRegistro(Long productosId,Long registrosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el registro con id = {0} del producto con id = " + productosId, registrosId);
        return persistence.find(productosId, registrosId);
    }
    
    /**
     * Actualizar un registro por ID
     *
     * @param registroId El ID del registro a actualizar
     * @param registroEntity La entidad del registro con los cambios deseados
     * @return La entidad del registro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public RegistroEntity updateRegistro(Long productosId, RegistroEntity registroEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el registro con id = {0} del producto con id = " + productosId, registroEntity.getId());
        if (!validateRegistro(registroEntity.getTipoRegistro())) {
           throw new BusinessLogicException("El ISBN es inválido");
        }
        ProductoEntity productoEntity = productoPersistence.find(productosId);
        registroEntity.setProducto(productoEntity);
        persistence.update(registroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el registro con id = {0} del producto con id = " + productosId, registroEntity.getId());
        return registroEntity;
    }
    
    /**
     * Eliminar un registro por ID
     *
     * @param registroId El ID del registro a eliminar
     */
    public void deleteRegistro(Long productosId, Long registrosId)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el registro con id = {0} del producto con id = " + productosId, registrosId);
        RegistroEntity old = getRegistro(productosId, registrosId);
        if (old == null) {
            throw new BusinessLogicException("El registro con id = " + registrosId + " no esta asociado a el producto con id = " + productosId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el registro con id = {0} del producto con id = " + productosId, registrosId);
    }
    
    /**
     * Valida qu un registro pertenezca a una de la categorias posibles
     * @param tipoRegistro el tipo de registro a validar
     * @return true si es valido, false de lo contrario
     */
    private boolean validateRegistro(String tipoRegistro) {
        return (tipoRegistro.equalsIgnoreCase(RegistroEntity.DESPACHO_CLIENTE)||tipoRegistro.equalsIgnoreCase(RegistroEntity.ORDEN_REAPROVISONAMIENTO) ||
                tipoRegistro.equalsIgnoreCase(RegistroEntity.PERDIDA) || tipoRegistro.equalsIgnoreCase(RegistroEntity.ROBO) || tipoRegistro.equalsIgnoreCase(RegistroEntity.TRASLADO_BODEGA)||
                tipoRegistro.equalsIgnoreCase(RegistroEntity.VENCIMIENTO));
    }
    

    
}
