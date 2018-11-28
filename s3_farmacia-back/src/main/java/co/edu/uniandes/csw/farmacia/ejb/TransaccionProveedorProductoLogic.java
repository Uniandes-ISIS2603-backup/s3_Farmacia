/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionProveedorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TransaccionProveedorProductoLogic {
    
    @Inject
    private TransaccionProveedorPersistence transaccionProveedorPersistence;
    
    @Inject
    private ProductoPersistence productoPersistence;
    
    /**
     * Asocia una transaccionProveedor a un Producto
     * @param idProveedor id del proveedor dueño de la transaccion
     * @param idTransaccionProveedor id de la transaccion del proveedor
     * @param idProducto id del producto a asociar
     * @throws BusinessLogicException en caso de que no se encuentre el proveedor o el producto
     */
    public void asociate(Long idProveedor, Long idTransaccionProveedor,
            Long idProducto) throws BusinessLogicException {
        TransaccionProveedorEntity transaccion = transaccionProveedorPersistence
                .find(idProveedor, idTransaccionProveedor);
        ProductoEntity producto = productoPersistence.find(idProducto);
        if (transaccion == null || producto == null) {
            throw new BusinessLogicException(
                    "No se encontraron los elementos a asociar");
        }    
          List<ProductoEntity> productos = transaccion.getProductos();
        productos.add(producto);
        transaccion.setProductos(productos);
        List<TransaccionProveedorEntity> transacciones = 
                producto.getTransaccionesProveedor();
        transacciones.add(transaccion);
        productoPersistence.update(producto);
        transaccionProveedorPersistence.update(transaccion);
    }
    
    /**
     * Desasocia la TransaccionProveedor y el Producto dados por parametro
     * @param idProveedor id del proveedor dueño de la transaccion
     * @param idTransaccionProveedor id de la transaccion del proveedor
     * @param idProducto id del producto a asociar
     * @throws BusinessLogicException en caso de que no se encuentre el proveedor o el producto,
     * o si no existe la asociación
     */
    public void deasociate(Long idProveedor, Long idTransaccionProveedor,
            Long idProducto) throws BusinessLogicException {
        TransaccionProveedorEntity transaccion = transaccionProveedorPersistence
                .find(idProveedor, idTransaccionProveedor);
        ProductoEntity producto = productoPersistence.find(idProducto);
        if (transaccion == null || producto == null) {
            throw new BusinessLogicException(
                    "No se encontraron los elementos a asociar");
        }
        List<ProductoEntity> productos = transaccion.getProductos();
        if(!productos.remove(producto)) {
            throw new BusinessLogicException("La asociacion no existe");
        }
        transaccion.setProductos(productos);
        transaccionProveedorPersistence.update(transaccion);
        producto.setTransaccionesProveedor(null);
    }
    
    /**
     * Retorna los productos asociados a la transaccion
     * @param idProveedor proveedor dueño de la transaccion
     * @param idTransaccionProveedor id de la transaccion proveedor
     * @return productos asociados a la transaccion
     * @throws BusinessLogicException si no existe la transaccion
     */
    public List<ProductoEntity> getProductos(Long idProveedor, 
            Long idTransaccionProveedor) throws BusinessLogicException {
        TransaccionProveedorEntity transaccion = transaccionProveedorPersistence
                .find(idProveedor, idTransaccionProveedor);
        if (transaccion == null) {
            throw new BusinessLogicException("No se encontró la transacción");
        }
        return transaccion.getProductos();
    }
    
    /**
     * Retorna la transaccion asociada al producto dado por parametro
     * @param idProducto id del producto a consultar
     * @return la transaccion asociada, null en caso de que el producto no se encuentre asociado
     * @throws BusinessLogicException si no se encuentra el producto
     */
    public List<TransaccionProveedorEntity> getTransaccion(Long idProducto)
            throws BusinessLogicException {
        ProductoEntity producto = productoPersistence.find(idProducto);
        if (producto == null) {
            throw new BusinessLogicException("No se encontró el producto");
        }
        return producto.getTransaccionesProveedor();
    }
}
