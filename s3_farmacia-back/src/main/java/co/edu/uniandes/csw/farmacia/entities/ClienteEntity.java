/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private String apellido;
    private String ciudad;
    private String direccionEnvio;
    private Long cedula;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<TransaccionClienteEntity> transaccionesCliente = new ArrayList<TransaccionClienteEntity>();
    
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
    
    public void setTransaccionesCliente(List<TransaccionClienteEntity> pTransacciones)
    {
        transaccionesCliente= pTransacciones;
    }
    
    public List<TransaccionClienteEntity> getTransaccionesCliente()
    {
        return transaccionesCliente;
    }
}
