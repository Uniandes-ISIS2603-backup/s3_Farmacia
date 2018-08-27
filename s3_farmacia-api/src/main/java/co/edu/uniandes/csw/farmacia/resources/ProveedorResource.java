/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProveedorDTO;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorResource extends ProveedorDTO 
{
   // @Inject
    // ProveedorLogic proveedorLogic;
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());

    /**
     *
     * @param proveedor
     * @return
     */
    @POST
    public ProveedorDTO createProveedor(ProveedorDTO proveedor) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ProveedorResource.createProveedor: input:{0}", proveedor.toString());
        ProveedorEntity provedEntity = proveedor.toEntity();
        
        ProveedorDTO nuevoProveedorDTO = new ProveedorDTO(provedEntity);
        
        return nuevoProveedorDTO;
     
    }
    @GET
    @Path("{id:\\ d+}")
    public ProveedorDTO getProveedor(Long id )
    {
        return null;
    }
    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id")Long id)
    {
        
    }
    @PUT
    @Path("{id:\\d+}")
    public ProveedorDTO refreshDataProveedor(Long id, ProveedorDTO proveedor)
    {
        return null;
    }
    

  
    
}
