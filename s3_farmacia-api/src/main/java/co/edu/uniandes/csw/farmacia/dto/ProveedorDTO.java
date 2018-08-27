/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;

/**
 *
 * @author estudiante
 */
public class ProveedorDTO
{
    private Long id;
    
    private String nombre;

    public ProveedorDTO() 
    {
        
    }
    
    public  ProveedorDTO(ProveedorEntity prove)
    {
        if (prove != null) {
            this.id = prove.getId();
            this.nombre = prove.getNombre();
        }
    }
    public ProveedorEntity toEntity()
    {
        
    }
    
    
    
    
}
