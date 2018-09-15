/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAMÓN
 */
public class TransaccionClienteDetailDTO extends TransaccionClienteDTO implements Serializable
{
    private List<ProductoDTO> carrito;
    
    public TransaccionClienteDetailDTO()
    {
        super();
    }
    public TransaccionClienteDetailDTO(TransaccionClienteEntity transaccion)
    {
        super(transaccion);
        if(transaccion.getProductos()!=null)
        {
            carrito = new ArrayList<>();
            for (ProductoEntity entityReview : transaccion.getProductos()) {
                carrito.add(new ProductoDTO(entityReview));
            }
        }
    }
    
    @Override
    public TransaccionClienteEntity toEntity() 
    {
        TransaccionClienteEntity transaccion = super.toEntity();
        if (carrito != null) {
            List<ProductoEntity> productos = new ArrayList<>();
            for (ProductoDTO dtoReview : getProductos()) {
                productos.add(dtoReview.toEntity());
            }
            transaccion.setProductos(productos);
        }
           return transaccion;
    }
      
    public List<ProductoDTO> getProductos()
    {
          return carrito;
      
    }
    
    public void setProductos(List<ProductoDTO> pProductos)
    {
        carrito= pProductos;
    }
}

