/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class TransaccionClienteProductosLogic {
    
    @Inject
    TransaccionClientePersistence transaccionClientePersistence;
    
    @Inject
    ProductoPersistence productoPersistence;
    
    /**
     * Asocia una transacción cliente con un producto, ambos ids dados por parámetro
     * @param idCliente id del cliente dueño de la transaccion
     * @param idTransaccionCliente id de la transaccion cliente
     * @param idProducto id del producto a asociar
     * @throws BusinessLogicException
     */
    public void asociate(Long idCliente, Long idTransaccionCliente, 
            Long idProducto) throws BusinessLogicException {
        ProductoEntity producto = productoPersistence.find(idProducto);
        TransaccionClienteEntity transaccionCliente = 
                transaccionClientePersistence
                        .find(idCliente, idTransaccionCliente);
        if (producto == null || transaccionCliente == null) {
            throw new BusinessLogicException("No se encontró el elemento");
        }
        List<ProductoEntity> productos = transaccionCliente.getProductos();
        productos.add(producto);
        transaccionCliente.setProductos(productos);
        List<TransaccionClienteEntity> transacciones = 
                producto.getTransaccionesCliente();
        transacciones.add(transaccionCliente);
        productoPersistence.update(producto);
        transaccionClientePersistence.update(transaccionCliente);
    }
    
    /**
     * Desasocia la transacción cliente con el producto dado por parámetro
     * @param idCliente id del cliente dueño de la transaccion
     * @param idTransaccionCliente id de la transaccionCliente
     * @param idProducto id del producto a desasociar
     * @throws BusinessLogicException si el elemento a eliminar no existe o
     *  si alguno de los elementos de la asociacion no existe
     */
    public void deasociate(Long idCliente, Long idTransaccionCliente, 
            Long idProducto) throws BusinessLogicException {
        ProductoEntity producto = productoPersistence.find(idProducto);
        TransaccionClienteEntity transaccionCliente = 
                transaccionClientePersistence
                        .find(idCliente, idTransaccionCliente);
        if (producto == null || transaccionCliente == null) {
            throw new BusinessLogicException("No se encontró el elemento");
        }
        List<ProductoEntity> productos = transaccionCliente.getProductos();
        if (!productos.remove(producto)) {
            throw new BusinessLogicException("No existe la asociación");
        }
        List<TransaccionClienteEntity> transacciones = 
                producto.getTransaccionesCliente();
        transacciones.remove(transaccionCliente);
        productoPersistence.update(producto);
        transaccionClientePersistence.update(transaccionCliente);
    }
    
    /**
     * Obtiene los productos asociados a la transaccion dada por parametro
     * @param idCliente id del cliente dueño de la transaccion
     * @param idTransaccionCliente id de la transaccion cliente
     * @return Productos asociados a la transaccion
     * @throws BusinessLogicException si no se encontró la transaccion
     */
    public List<ProductoEntity> getProductos(Long idCliente, 
            Long idTransaccionCliente) throws BusinessLogicException {
        TransaccionClienteEntity transaccionCliente = 
                transaccionClientePersistence
                        .find(idCliente, idTransaccionCliente);
        if (transaccionCliente == null)
            throw new BusinessLogicException(
                    "No se encontró la transacción del cliente");
        return transaccionCliente.getProductos();
    }
    
    /**
     * Obtiene las transaccionesCliente asociadas al producto dado por parametro
     * @param idProducto id del producto a consultar
     * @return transacciones asociadas
     * @throws BusinessLogicException si no se encontró el producto
     */
    public List<TransaccionClienteEntity> getTransacciones(Long idProducto)
            throws BusinessLogicException {
        ProductoEntity producto = productoPersistence.find(idProducto);
        if (producto == null)
            throw new BusinessLogicException("No se encontró el producto");
        return producto.getTransaccionesCliente();
    }
}
