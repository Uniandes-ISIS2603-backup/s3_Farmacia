/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author fj.gonzalez
 */
@Stateless
public class ProveedorProductosLogic 
{
    /*
        private static final Logger LOGGER = Logger.getLogger(ProveedorProductosLogic.class.getName());

    private ProveedorPersistence proveedorPersistence;
    
    private ProductoPersistence productoPersistence;
    
    public List<ProductoEntity> getProductos(Long proveedorId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los productos  del proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getProductos();
    }
    public ProductoEntity getProducto(Long proveedorId, Long productoId)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar el producto de un proveedor con id ={0}", proveedorId);
        List<ProductoEntity> productos = proveedorPersistence.find(proveedorId).getProductos();
        ProductoEntity proEntity = productoPersistence.find(productoId);
        int index = productos.indexOf(proEntity);
        if(index >= 0)
        {
            return productos.get(index);
        }
        return null;
    }
    
        public ProductoEntity addProducto(Long proveedorId, Long productoId) 
        {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un producto al proveedor con id = {0}", proveedorId);
        ProductoEntity productoEntity = productoPersistence.find(productoId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        proveedorEntity.getProductos().add(productoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un producto al proveedor con id = {0}", proveedorId);
        return productoPersistence.find(productoId);
            
        }
        
        public List<ProductoEntity> replaceProductos(Long provId, List<ProductoEntity> list) 
        {
         LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los productos del proveedor con id = {0}", provId);
            ProveedorEntity proveedorEntity = proveedorPersistence.find(provId);
            proveedorEntity.setProductos(list);
            LOGGER.log(Level.INFO, "Termina proceso de reemplazar los productos del proveedor con id = {0}", provId);
            return proveedorPersistence.find(provId).getProductos();
        }
        
        public void removeProducto(Long proveedorId, Long productoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un producto del proveedor con id = {0}", proveedorId);
        ProductoEntity productoEntity = productoPersistence.find(authorsId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        proveedorEntity.getProductos().remove(productoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de  borrar un producto del proveedor con  id = {0}", proveedorId);
    }
    
    */
    
    
}
