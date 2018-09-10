/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable
{
    private List<ProductoDTO> productos;
    
    public ProveedorDetailDTO()
    {
        
    }
    
    public ProveedorDetailDTO(ProveedorEntity proveedorEntity)
    {
        super(proveedorEntity);
        if(proveedorEntity != null)
        {
         //   if(proveedorEntity.getProductos() = null)
         //  {
         //       productos = new ArrayList<>();
         //     for(ProductorEntity entityProduct : proveedorEntity.getProducts())
           //   {
            //      productos.add(new ProductoDTO(entityProduct));
            //
        //      }
        }
         //   }
         
        
    }
    @Override
    public ProveedorEntity toEntity()
    {
         ProveedorEntity proveedorEntity = super.toEntity();
      //  if (productos != null) {
        //    List<ProductoEntity> productosEntity = new ArrayList<>();
        //    for (ProductoDTO dtoProduct : productos) {
        //        productosEntity.add(dtoProduct.toEntity());
        //    }
         //   proveedorEntity.setBooks(productosEntity);
     //   }
        return proveedorEntity;
    }
    
    public List<ProductoDTO> getProductos()
    {
        return productos;
    }
    public void setProductos(List<ProductoDTO> pProducts)
    {
        this.productos = pProducts;
    }
    
}

