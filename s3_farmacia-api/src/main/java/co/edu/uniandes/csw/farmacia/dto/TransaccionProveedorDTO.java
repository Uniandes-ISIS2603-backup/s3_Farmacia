/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TransaccionProveedorDTO . Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "monto": number,
 *      "tiempo": string,
 *      "proveedor": {@link ProveedorDTO}
 *   }
 * </pre> Por ejemplo una reseña se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123,
 *      "monto": 4000,
 *      "tiempo": "40",
 *      "proveedor":
 *      {
 *          "id": 123,
 *          "nombre" : "Nestle"
 *      }
 *   }
 *
 * </pre>
 *
 * @author ISIS2603
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.io.Serializable;

/**
 *
 * @author jd.florezg1
 */
public class TransaccionProveedorDTO implements Serializable {
    
    //Atributos
    
    protected long id;
    /**
     * Cantidad de dinero manejada en la transacción
     */
    protected Double monto;
    
    
    /**
     * Tiempo que tomo realizar la transacción
     */
    protected String tiempo;
        
    private ProveedorDTO proveedor;
    
    //Constructor
    public TransaccionProveedorDTO()
    {
        
    }
    
    public TransaccionProveedorDTO(TransaccionProveedorEntity trans)
    {
        if (trans != null) {
            this.id = trans.getId();
            this.tiempo = trans.getTiempo();
            this.monto = trans.getMonto();
            if(trans.getProveedor() != null)
            {
                this.proveedor = new ProveedorDTO(trans.getProveedor());
            } else {
                this.proveedor = null;
            }
        }
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TransaccionProveedorEntity toEntity() {
        TransaccionProveedorEntity transaccionProveedorEntity = new TransaccionProveedorEntity();
        transaccionProveedorEntity.setId(this.id);
        transaccionProveedorEntity.setMonto(this.monto);
        transaccionProveedorEntity.setTiempo(this.tiempo);
        if (this.proveedor != null) {
            transaccionProveedorEntity.setProveedor(this.proveedor.toEntity());
        }
        return transaccionProveedorEntity;
    }
    
    /**
     * @return Cantidad de dinero manejada en la transacción.
     */
    public double getMonto()
    {
        return monto;
    }
    
    public ProveedorDTO getProveedor()
    {
        return proveedor;
    }
    
    public void setProveedor(ProveedorDTO proveedor)
    {
        this.proveedor = proveedor;
    }
    
    public double getId()
    {
        return id;
    }
    
    /**
     * @return Cantidad de tiempo que tardo la transacción.
     */
    public String getTiempo()
    {
        return tiempo;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    /**
     * Cambia la cantidad de dinero por la que llega por parametro.
     * @param monto Cantidad de dinero nueva.
     */
    public void setMonto(Double monto)
    {
        this.monto = monto;
    }
    
    /**
     * Cambia el tiempo por el que llega por parámetro
     * @param tiempo Tiempo nuevo.
     */
    public void setTiempo(String tiempo)
    {
        this.tiempo = tiempo;
    }
}
