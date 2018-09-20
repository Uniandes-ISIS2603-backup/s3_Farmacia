/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class ProductoDetailDTO extends ProductoDTO {
    private List<RegistroDTO> registros;
    
    private ProveedorDTO proveedor;

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public List<TransaccionClienteDTO> getTransaccionCliente() {
        return transaccionCliente;
    }

    public void setTransaccionCliente(List<TransaccionClienteDTO> transaccionCliente) {
        this.transaccionCliente = transaccionCliente;
    }

    public TransaccionProveedorDTO getTransaccionProveedor() {
        return transaccionProveedor;
    }

    public void setTransaccionProveedor(TransaccionProveedorDTO transaccionProveedor) {
        this.transaccionProveedor = transaccionProveedor;
    }
    
    private List<TransaccionClienteDTO> transaccionCliente;
    
    private TransaccionProveedorDTO transaccionProveedor;

    public ProductoDetailDTO(ProductoEntity producto) {
        super(producto);
        if (producto != null) {
            this.proveedor = new ProveedorDTO(producto.getProveedor());
            this.transaccionProveedor = new TransaccionProveedorDTO(
                    producto.getTransaccionProveedor());
            if (producto.getRegistros() != null) {
                this.registros = new ArrayList<>();
                for (RegistroEntity registro : producto.getRegistros()) {
                    this.registros.add(new RegistroDTO(registro));
                }
            }
            if (producto.getTransaccionesCliente() != null) {
                this.transaccionCliente = new ArrayList<>();
                for (TransaccionClienteEntity transaccion : 
                        producto.getTransaccionesCliente()) {
                    this.transaccionCliente
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
