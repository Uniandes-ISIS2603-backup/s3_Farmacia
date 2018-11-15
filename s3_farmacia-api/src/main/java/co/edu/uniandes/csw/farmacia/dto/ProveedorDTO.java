/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * EditorialDTO Objeto de transferencia de datos de Proveedores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string
 *   }
 * </pre> Por ejemplo un proveedor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": "Nestle"
 *   }
 *
 * </pre>
 *
 * @author fj.gonzalez
 */
public class ProveedorDTO implements Serializable {

    /**
     * Id del proveedor
     */
    private Long id;
    /**
     * Nombre del proveedor
     */
    private String nombre;

    /**
     * Constructo vacio del proveedor
     */
    public ProveedorDTO() {
        //
    }

    /**
     * Constructor
     *
     * @param prove La representación entidad del proveedor a crear
     */
    public ProveedorDTO(ProveedorEntity prove) {
        if (prove != null) {
            this.id = prove.getId();
            this.nombre = prove.getNombre();
        }
    }

    /**
     * Convertir el proveedor del api a uno que pueda ser leido por el back
     *
     * @return el proveedorEntity
     */
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setId(this.id);
        proveedorEntity.setNombre(this.nombre);
        return proveedorEntity;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Establece el valor del atributo nombre.
     *
     * @param pNombre
     *
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Obteniene el id del proveedor
     *
     * @return el id del proveedor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del proveedor.
     * @return el nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo toString del proveedor
     *
     * @return la cadena de texto que identifica al proveedor
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
