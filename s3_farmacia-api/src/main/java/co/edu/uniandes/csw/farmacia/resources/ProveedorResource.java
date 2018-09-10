/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProveedorDTO;
import co.edu.uniandes.csw.farmacia.dto.ProveedorDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorLogic;
import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.logging.Level;
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
 * @author Francisco
 */
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorResource {

    @Inject
    private ProveedorLogic proveedorLogic;

    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());

    /**
     *
     * @param proveedor
     * @return
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @POST
    public ProveedorDTO createProveedor(ProveedorDTO proveedor) throws BusinessLogicException {
        // LOGGER.log(Level.INFO, "ProveedorResource.createProveedor: input:{0}", proveedor.toString());
        // ProveedorEntity editorialEntity = proveedor.toEntity();
        //  ProveedorEntity nuevoProveedorEntity = proveedorLogic.createEditorial(editorialEntity);
        // ProveedorDTO nuevoProveedorDTO = new ProveedorDTO(nuevoProveedorEntity);
        // LOGGER.log(Level.INFO, "ProveedorResource createProveedor: output: {0}", nuevoProveedorDTO.toString());
        // return nuevoProveedorDTO;
        return proveedor;
    }

    @GET
    public ProveedorDTO getProveedores() {
        return new ProveedorDTO();
    }

    @GET
    @Path("{id: \\d+}")
    public ProveedorDTO getProveedor(@PathParam("id") Long id) {
        LOGGER.log(Level.INFO, "ProveedorResource getEditorial: input: {0}", id);
        // ProveedorEntity proveedorEntity = proveedorLogic.getProveedor(id);
        // if (proveedorEntity == null) {
        //   throw new WebApplicationException("El recurso /proveedores/" + id + " no existe.", 404);

        // ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(proveedorEntity);
        // LOGGER.log(Level.INFO, "ProveedorResource getEditorial: output: {0}", detailDTO.toString());
        // return detailDTO;
        // ProveedorDTO p = new ProveedorDTO(new ProveedorEntity());
        //p.setId(id);
        // p.setNombre("Bayer");
//        System.out.println("co.edu.uniandes.csw.farmacia.resources.ProveedorResource.getProveedor()"+p.getNombre());
        //Este el caso de prueba para verificar que si sirvan las pruebas de Postman
        //      return p;
        return null;
    }

    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id") Long id) {

    }

    @PUT
    @Path("{id:\\d+}")
    public ProveedorDTO refreshDataProveedor(@PathParam("id") Long id, ProveedorDTO proveedor) {
        return proveedor;
    }

}
