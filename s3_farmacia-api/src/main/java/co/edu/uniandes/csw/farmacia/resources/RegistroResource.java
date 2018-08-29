/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.RegistroDTO;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
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
@Path("registros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RegistroResource {
    
    private static final Logger LOGGER = Logger.getLogger(RegistroResource.class.getName());
    
    @POST
    public RegistroDTO createRegistro(RegistroDTO registro) throws BusinessLogicException{
       return registro; 
    }
    
    @GET
    public RegistroDTO getRegistros(){
        return new RegistroDTO();
    }
    
    @GET
    @Path("{id: \\d+}")
    public RegistroDTO getRegistro(@PathParam("id") Long id){
        RegistroDTO r = new RegistroDTO(new RegistroEntity());
        r.setId(id);
        r.setCantidad(7);
        System.out.println("co.edu.uniandes.csw.farmacia.resources.RegistroResource.getRegistro()"+r.getCantidad());
        return r;
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteRegistro(@PathParam("id") Long id){
        
    }
    
    @PUT
    @Path("{id:\\d+}")
    public RegistroDTO refrshDataProvedor(@PathParam("id") Long id, RegistroDTO registro){
        return registro;
    }
    
}
