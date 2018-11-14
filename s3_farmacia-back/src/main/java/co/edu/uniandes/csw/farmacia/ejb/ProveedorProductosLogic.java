/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.ProveedorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fj.gonzalez
 */


@Stateless
public class ProveedorProductosLogic {

    private static final Logger LOGGER = Logger.getLogger(ProveedorProductosLogic.class.getName());

    @Inject
    private ProveedorPersistence proveedorPersistence;

    @Inject
    private ProductoPersistence productoPersistence;

    public List<ProductoEntity> getProductos(Long proveedorId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los productos  del proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getProductos();
    }

    public ProductoEntity getProducto(Long proveedorId, Long productoId)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar el producto de un proveedor con id ={0}", proveedorId);
        List<ProductoEntity> productos = proveedorPersistence.find(proveedorId).getProductos();
        ProductoEntity proEntity = productoPersistence.find(productoId);
        int index = productos.indexOf(proEntity);
        if (index >= 0) {
            return productos.get(index);
        }
        throw new BusinessLogicException("El producto no está asociado al proveedor.");
    }

    public ProductoEntity addProducto(Long proveedorId, Long productoId) {
       LOGGER.log(Level.INFO, "Inicia proceso de agregarle un producto al proveedor con id = {0}", proveedorId);
        ProveedorEntity provEntity = proveedorPersistence.find(proveedorId);
        ProductoEntity productoEntity = productoPersistence.find(productoId);
        productoEntity.getProveedores().add(provEntity);
        provEntity.getProductos().add(productoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un producto al proveedor con id = {0}", proveedorId);
        return productoPersistence.find(productoId);

    }

    public List<ProductoEntity> replaceProductos(Long provId, List<ProductoEntity> list) {
               LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", provId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(provId);
        List<ProductoEntity> productoList = productoPersistence.list();
        for (ProductoEntity p : productoList) {
            if (list.contains(p)) {
                List<ProveedorEntity> proveedores = new ArrayList<>();
                proveedores.add(proveedorEntity);
                p.setProveedores(proveedores);
            } else if (p.getProveedores()!= null && p.getProveedores().equals(proveedorEntity)) {
                p.setProveedores(null);
            }
        }
        proveedorEntity.setProductos(productoList);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", provId);
        return proveedorEntity.getProductos();
    }

    public void removeProducto(Long proveedorId, Long productoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un producto del proveedor con id = {0}", proveedorId);
        ProductoEntity productoEntity = productoPersistence.find(productoId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        proveedorEntity.getProductos().remove(productoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de  borrar un producto del proveedor con  id = {0}", proveedorId);
    }    
}
