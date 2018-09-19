/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link BookDTO} para manejar las relaciones entre los
 * BookDTO y otros DTOs. Para conocer el contenido de la un Libro vaya a la
 * documentacion de {@link BookDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "monto": number,
 *      "tiempo": string,
 *      "proveedor": {@link ProveedorDTO},
 *      "productos": [{@link ProductoDTO}],
 *   }
 * 
 **/
/**
 *
 * @author jd.florezg1
 */
public class TransaccionProveedorDetailDTO extends TransaccionProveedorDTO implements Serializable {

    private List<ProductoDTO> productos;

    public TransaccionProveedorDetailDTO() {
        super();
    }

    public TransaccionProveedorDetailDTO(TransaccionProveedorEntity transaccionProveedorEntity) {
        super(transaccionProveedorEntity);
        if (transaccionProveedorEntity != null) {
            if (transaccionProveedorEntity.getProductos() == null) {
                productos = new ArrayList<>();
                for (ProductoEntity entityProduct : transaccionProveedorEntity.getProductos()) {
                    productos.add(new ProductoDTO(entityProduct));

                }
            }
        }
    }
    
    @Override
    public TransaccionProveedorEntity toEntity() 
    {
        TransaccionProveedorEntity transaccion = super.toEntity();
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