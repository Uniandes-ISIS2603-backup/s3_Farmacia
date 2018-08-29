/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;


/**
 *
 * @author estudiante
 */
@Entity 
public class ProveedorEntity extends BaseEntity implements Serializable
{
    private String nombre;
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorEntity.class.getName());

    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    public String getNombre()
    {
        return nombre;
    }
}
