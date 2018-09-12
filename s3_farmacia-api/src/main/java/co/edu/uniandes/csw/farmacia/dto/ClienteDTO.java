/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class ClienteDTO implements Serializable {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String direccionEnvio;
    private Long cedula;
    
    public ClienteDTO(){
        
    }
    
    public ClienteDTO ( ClienteEntity clienteEntity){
        if (clienteEntity != null){
            this.id = clienteEntity.getId();
            this.nombre = clienteEntity.getNombre();
            this.apellido = clienteEntity.getApellido();
            this.ciudad = clienteEntity.getCiudad();
            this.direccionEnvio = clienteEntity.getDireccionEnvio();
        }
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getCiudad(){
        return ciudad;
    }
    
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    
    public String getDireccionEnvio(){
        return direccionEnvio;
    }
    
    public void setDireccionEnvio(String direccionEnvio){
        this.direccionEnvio = direccionEnvio;
    }
    
    public Long getCedula(){
        return cedula;
    }
    
    public void setCedula(Long cedula){
        this.cedula = cedula;
    }
    
    public ClienteEntity toEntity(){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(this.id);
        clienteEntity.setApellido(this.apellido);
        clienteEntity.setNombre(this.nombre);
        clienteEntity.setCiudad(this.ciudad);
        clienteEntity.setDireccionEnvio(this.direccionEnvio);
        clienteEntity.setCedula(this.cedula);
        return clienteEntity;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
