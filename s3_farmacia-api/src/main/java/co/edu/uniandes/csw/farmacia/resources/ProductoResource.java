/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import co.edu.uniandes.csw.farmacia.dto.ProductoDTO;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;

/**
 *
 * @author estudiante
 */
@Path("productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProductoResource {
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    @POST
    public ProductoDTO createProducto(ProductoDTO producto)
            throws BusinessLogicException {
        return producto;
    }
    
    @GET
    public ProductoDTO getProductos() {
        return new ProductoDTO();
    }
    
    @GET
    @Path("{productosId:\\d+}")
    public ProductoDTO getProducto(@PathParam("productosId") Long id) {
        ProductoDTO producto = new ProductoDTO(new ProductoEntity());
        producto.setId(id);
        return producto;
    }
    
    @DELETE
    @Path("{productosId:\\d+}")
    public void deleteProducto(@PathParam("productosId") Long id){
        
    }
    
    @PUT
    @Path("{productosId: \\d+}")
    public ProductoDTO refreshDataCliente ( @PathParam("productosId") 
            Long productoId, 
            ProductoDTO producto)throws WebApplicationException{
        return producto;
    } 
}
