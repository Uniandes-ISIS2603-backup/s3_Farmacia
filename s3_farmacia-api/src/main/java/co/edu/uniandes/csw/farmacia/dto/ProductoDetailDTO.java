/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
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

    public TransaccionClienteDTO getTransaccionCliente() {
        return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionClienteDTO transaccionCliente) {
        this.transaccionCliente = transaccionCliente;
    }

    public TransaccionProveedorDTO getTransaccionProveedor() {
        return transaccionProveedor;
    }

    public void setTransaccionProveedor(TransaccionProveedorDTO transaccionProveedor) {
        this.transaccionProveedor = transaccionProveedor;
    }
    
    private TransaccionClienteDTO transaccionCliente;
    
    private TransaccionProveedorDTO transaccionProveedor;

    public ProductoDetailDTO(ProductoEntity producto) {
        super(producto);
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
