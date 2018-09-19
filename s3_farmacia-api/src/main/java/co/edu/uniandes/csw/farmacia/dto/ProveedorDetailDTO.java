/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fj.gonzalez
 */
/**
 * Clase que extiende de {@link BookDTO} para manejar las relaciones entre los
 * BookDTO y otros DTOs. Para conocer el contenido de la un Libro vaya a la
 * documentacion de {@link BookDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "productos": [{@link ProductoDTO}],
 *      "transaccionesProveedor": [{@link TransaccionProveedorDTO}]
 *   }
 **/
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable
{
    private List<ProductoDTO> productos;
    
    private List<TransaccionProveedorDTO> transaccionesProveedor;
    
    public ProveedorDetailDTO()
    {
        super();
    }
    
    public ProveedorDetailDTO(ProveedorEntity proveedorEntity)
    {
        super(proveedorEntity);
        if ( proveedorEntity.getProductos() != null) 
        {
            productos = new ArrayList<>();
            for(ProductoEntity entityProducto : proveedorEntity.getProductos()) 
            {
                productos.add(new ProductoDTO(entityProducto));
            }
        }
        if ( proveedorEntity.getTransacciones() != null) 
        {
            transaccionesProveedor = new ArrayList<>();
            for (TransaccionProveedorEntity entityTransaccion : proveedorEntity.getTransacciones()) 
            {
                transaccionesProveedor.add(new TransaccionProveedorDTO(entityTransaccion));
            }
        }
    }
    @Override
    public ProveedorEntity toEntity()
    {
         ProveedorEntity proveedorEntity = super.toEntity();
        if (productos != null)
        {
            List<ProductoEntity> productosEntity = new ArrayList<>();
            for (ProductoDTO dtoProduct : productos) {
                productosEntity.add(dtoProduct.toEntity());
            }
            proveedorEntity.setProductos(productosEntity);
        }
        if (transaccionesProveedor != null)
        {
            List<TransaccionProveedorEntity> transaccionesEntity = new ArrayList<>();
            for (TransaccionProveedorDTO dtoTrans : transaccionesProveedor) {
                transaccionesEntity.add(dtoTrans.toEntity());
            }
            proveedorEntity.setTransacciones(transaccionesEntity);
        }
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

