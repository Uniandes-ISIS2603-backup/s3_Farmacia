/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class ProductoDetailDTO extends ProductoDTO implements Serializable {
    private List<RegistroDTO> registros;
    
    private List<ProveedorDTO> proveedor;

    public List<ProveedorDTO> getProveedor() {
        return proveedor;
    }

    public void setProveedor(List<ProveedorDTO> proveedor) {
        this.proveedor = proveedor;
    }

    public List<TransaccionClienteDTO> getTransaccionCliente() {
        return transaccionesCliente;
    }

    public void setTransaccionCliente(List<TransaccionClienteDTO> transaccionCliente) {
        this.transaccionesCliente = transaccionCliente;
    }

    public List<TransaccionProveedorDTO> getTransaccionProveedor() {
        return transaccionesProveedor;
    }

    public void setTransaccionProveedor(List<TransaccionProveedorDTO> transaccionProveedor) {
        this.transaccionesProveedor = transaccionProveedor;
    }
    
    private List<TransaccionClienteDTO> transaccionesCliente;
    
    private List<TransaccionProveedorDTO> transaccionesProveedor;

    public ProductoDetailDTO(ProductoEntity producto) {
        super(producto);
        if (producto != null) {
            List<ProveedorDTO> proveedores = new ArrayList<>();
            for (ProveedorEntity prov : producto.getProveedor()) {
                proveedores.add(new ProveedorDTO(prov));
            }
            this.proveedor = proveedores;
            List<TransaccionProveedorDTO> transacciones = new ArrayList<>();
            for (TransaccionProveedorEntity trans: producto.getTransaccionProveedor()) {
                transacciones.add(new TransaccionProveedorDTO(trans));
            }
            this.transaccionesProveedor = transacciones;
            if (producto.getRegistros() != null) {
                this.registros = new ArrayList<>();
                for (RegistroEntity registro : producto.getRegistros()) {
                    this.registros.add(new RegistroDTO(registro));
                }
            }
            if (producto.getTransaccionesCliente() != null) {
                this.transaccionesCliente = new ArrayList<>();
                for (TransaccionClienteEntity transaccion : 
                        producto.getTransaccionesCliente()) {
                    this.transaccionesCliente
                            .add(new TransaccionClienteDTO(transaccion));
                }
            }
        }
    }

    public ProductoDetailDTO() {
    }

    
    
    public List<RegistroDTO> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroDTO> registros) {
        this.registros = registros;
    }
}
