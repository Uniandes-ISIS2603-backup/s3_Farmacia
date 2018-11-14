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
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "productos": [{@link ProductoDTO}],
 *      "transaccionesProveedor": [{@link TransaccionProveedorDTO}]
 *   }
 **/
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable
{
    /**
     * Productos ofrecidos por el proveedor
     */
    private List<ProductoDTO> productos;
    
    /**
     * Transacciones ejecutadas por un proveedor
     */
    private List<TransaccionProveedorDTO> transaccionesProveedor;
    
    /**
     * Constructor vacio
     */
    public ProveedorDetailDTO()
    {
        super();
        productos = new ArrayList<>();
        transaccionesProveedor = new ArrayList<>();
    }
    /**
     * Constructor 
     * @param proveedorEntity 
     */
    public ProveedorDetailDTO(ProveedorEntity proveedorEntity)
    {
        super(proveedorEntity);
        productos = new ArrayList<>();
        transaccionesProveedor = new ArrayList<>();
        if ( proveedorEntity.getProductos() != null) 
        {
            for(ProductoEntity entityProducto : proveedorEntity.getProductos()) 
            {
                productos.add(new ProductoDTO(entityProducto));
            }
        }
        if ( proveedorEntity.getTransacciones() != null) 
        {
            for (TransaccionProveedorEntity entityTransaccion : proveedorEntity.getTransacciones()) 
            {
                transaccionesProveedor.add(new TransaccionProveedorDTO(entityTransaccion));
            }
        }
    }
    /**
     * Retorna la entidad asociada al dto con toda su información respectiva.
     * @return La entidad asociado al dto.
     */
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
    /**
     * Obtener productos del proveedor
     * @return los productos
     */
    public List<ProductoDTO> getProductos()
    {
        return productos;
    }
    /**
     * Modificar la lista de productos del proveedor.
     * @param pProducts Productos a adicionar
     */
    public void setProductos(List<ProductoDTO> pProducts)
    {
        this.productos = pProducts;
    }
    /**
     * Obtener transacciones del proveedor
     * @return las transacciones del proveedor.
     */
    public List<TransaccionProveedorDTO> getTransaccionesProveedor()
    {
        return transaccionesProveedor;
    }
    /**
     * Modificar la lista de transacciones del proveedor
     * @param pListaTrans la lista nueva de transacciones.
     */
    public void setTransaccionesProveedor(List<TransaccionProveedorDTO> pListaTrans )
    {
        this.transaccionesProveedor = pListaTrans;
    }
    
}

