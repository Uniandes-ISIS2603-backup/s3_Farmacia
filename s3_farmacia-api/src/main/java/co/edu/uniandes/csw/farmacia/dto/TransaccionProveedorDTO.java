/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    /**
     * Cantidad de dinero manejada en la transacción
     */
    protected Double monto;
    
    
    /**
     * Tiempo que tomo realizar la transacción
     */
    protected String tiempo;
    
    protected long id;
    
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
        return transaccionProveedorEntity;
    }
    
    /**
     * @return Cantidad de dinero manejada en la transacción.
     */
    public double getMonto()
    {
        return monto;
    }
    
    /**
     * @return Cantidad de tiempo que tardo la transacción.
     */
    public String darTiempo()
    {
        return tiempo;
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
