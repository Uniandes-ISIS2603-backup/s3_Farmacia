/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.TransaccionClientePersistence;
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
     * @param idTransaccionCliente id de la transaccion cliente
     * @param idProducto id del producto a asociar
     */
    public void asociate(Long idTransaccionCliente, Long idProducto) {
        ProductoEntity producto = productoPersistence.find(idProducto);
        //producto.
    }
}
