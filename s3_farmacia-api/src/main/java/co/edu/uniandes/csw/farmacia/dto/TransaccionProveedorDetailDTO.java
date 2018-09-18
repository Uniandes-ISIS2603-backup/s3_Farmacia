/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class TransaccionProveedorDetailDTO extends TransaccionProveedorDTO implements Serializable {
    
    private List<ProductoDTO> productos;
    
    public TransaccionProveedorDetailDTO()
    {
        
    }
    
    public TransaccionProveedorDetailDTO(TransaccionProveedorEntity transaccionProveedorEntity)
    {
        super(transaccionProveedorEntity);
        if(transaccionProveedorEntity != null)
        {
         //   if(transaccionProveedorEntity.getProductos() = null)
         //  {
         //       productos = new ArrayList<>();
         //     for(ProductoEntity entityProduct : transaccionProveedorEntity.getProductos())
           //   {
            //      productos.add(new ProductoDTO(entityProduct));
            //
        //      }
        }
    }
}
