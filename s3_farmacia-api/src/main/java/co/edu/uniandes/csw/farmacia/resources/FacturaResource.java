package co.edu.uniandes.csw.farmacia.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.farmacia.dto.ClienteDTO;
import co.edu.uniandes.csw.farmacia.dto.FacturaDTO;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author estudiante
 */
@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource {
    
    private static final Logger LOGGER = Logger.getLogger(FacturaResource.class.getName());
    
    @POST
    public FacturaDTO createFactura(FacturaDTO factura)throws BusinessLogicException
    {
        return factura;
    }
    
    @GET
    public FacturaDTO getFacturas(){
        return new FacturaDTO();
    }
    
    @GET
    @Path("{id: \\d+}")
    public FacturaDTO getFactura(@PathParam("id") Long id){

        FacturaDTO f = new FacturaDTO();
        f.setId(id);
        f.setFecha("29/08/2018");
        f.setPrecio(20.200);
        f.setProductos("Acetaminofen");
        f.setUnidades(Integer.SIZE);
        f.setTipo(FacturaDTO.TipoFactura.DESPACHO);
        System.out.println("co.edu.uniandes.csw.farmacia.resources.ClienteResource.getCliente()"+f.getProductos());
        return f;
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteFactura(@PathParam("id") Long id){
        
    }
    
    @PUT
    @Path("{facturasId:\\d+}")
    public FacturaDTO refreshDataCliente ( @PathParam("facturasId") Long clientesId, FacturaDTO factura)throws WebApplicationException{
        return factura;
    } 
    
    
}
