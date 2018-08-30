/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ClienteDTO;
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    //@Inject
    //ClienteLogic clienteLogic;
    
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws BusinessLogicException{
        return cliente;
    }
    
    @GET
    public ClienteDTO getClientes(){
        return new ClienteDTO();
    }
    
    @GET
    @Path("{id: \\d+}")
    public ClienteDTO getCliente(@PathParam("id") Long id){
        ClienteDTO c = new ClienteDTO(new ClienteEntity());
        c.setId(id);
        c.setNombre("Harry");
        c.setApellido("Potter");
        c.setCiudad("Privet Drive");
        c.setDireccionEnvio("Cra6#1-39");
        System.out.println("co.edu.uniandes.csw.farmacia.resources.ClienteResource.getCliente()"+c.getNombre());
        return c;
    }
    
    @DELETE
    @Path("{id:\\d+}")
    public void deleteCliente(@PathParam("id") Long id){
        
    }
    
    @PUT
    @Path("{clientesId:\\d+}")
    public ClienteDTO refreshDataCliente ( @PathParam("clientesId") Long clientesId, ClienteDTO cliente)throws WebApplicationException{
        return cliente;
    } 
    
}
