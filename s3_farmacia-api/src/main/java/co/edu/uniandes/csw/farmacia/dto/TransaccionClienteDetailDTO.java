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
    private List<ProductoDTO> productos;    
    
    public TransaccionClienteDetailDTO()
    {
        super();
    }
    
    public TransaccionClienteDetailDTO(TransaccionClienteEntity transaccion)
    {
        super(transaccion);
        if(transaccion.getProductos()!=null)
        {
            productos = new ArrayList<>();
            for (ProductoEntity entityReview : transaccion.getProductos()) {
                productos.add(new ProductoDTO(entityReview));
            }
        }
    }
    
    
    @Override
    public TransaccionClienteEntity toEntity() 
    {
        TransaccionClienteEntity transaccion = super.toEntity();
        if (productos != null) {
            List<ProductoEntity> productos1 = new ArrayList<>();
            for (ProductoDTO dtoReview : getProductos()) {
                productos1.add(dtoReview.toEntity());
            }
            transaccion.setProductos(productos1);
        }
           return transaccion;
    }

      
    public List<ProductoDTO> getProductos()
    {
          return productos;
      
    }
    
    public void setProductos(List<ProductoDTO> pProductos)
    {
        productos = pProductos;
    }
}

