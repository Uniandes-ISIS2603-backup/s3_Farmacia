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
import java.util.ArrayList;
import java.util.List;
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

    private static final String A1 = "El recurso /proveedores/";

    private static final String A2 = " no existe.";

    /**
     * Retorna el proveedor creado con su informacion basica
     *
     * @param proveedor El proveedor a ingresar
     * @return el dto del proveedor ingresado
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @POST
    public ProveedorDTO createProveedor(ProveedorDTO proveedor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorResource.createProveedor: input:{0}", proveedor);
        ProveedorEntity proveedorEntity = proveedor.toEntity();
        ProveedorEntity nuevoProveedorEntity = proveedorLogic.createProveedor(proveedorEntity);
        ProveedorDTO nuevoProveedorDTO = new ProveedorDTO(nuevoProveedorEntity);
        LOGGER.log(Level.INFO, "ProveedorResource createProveedor: output: {0}", nuevoProveedorDTO);
        return nuevoProveedorDTO;
    }

    /**
     * Retorna la lista de proveedores
     *
     * @return la lista de los proveedores con su detalle
     */
    @GET
    public List<ProveedorDetailDTO> getProveedores() {
        LOGGER.info("ProveedorResource getProveedores: input: void");
        List<ProveedorDetailDTO> listaProveedores = listEntity2DetailDTO(proveedorLogic.getProveedores());
        LOGGER.log(Level.INFO, "ProveedorResource getProveedores: output : {0}", listaProveedores);
        return listaProveedores;
    }

    /**
     * Retorna el detalle del proveedor con el id especificado.
     *
     * @param id el id del proveedor que se desea obtener
     * @return proveedor
     */
    @GET
    @Path("{id: \\d+}")
    public ProveedorDetailDTO getProveedor(@PathParam("id") Long id) {
        LOGGER.log(Level.INFO, "ProveedorResource getEditorial: input: {0}", id);
        ProveedorEntity proveedorEntity = proveedorLogic.getProveedor(id);
        if (proveedorEntity == null) {
            throw new WebApplicationException(A1 + id + A2, 404);
        }

        ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(proveedorEntity);
        LOGGER.log(Level.INFO, "ProveedorResource getEditorial: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Elimina el proveedor con el id especificado.
     *
     * @param id el id del proveedor a eliminar.
     * @throws BusinessLogicException Si el proveedor que se desea eliminar no
     * existe
     */
    @DELETE
    @Path("{id:\\d+}")
    public void deleteProveedor(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorResource deleteProveedor: input{0}", id);
        if (proveedorLogic.getProveedor(id) == null) {
            throw new WebApplicationException(A1 + id + A2, 404);
        }
        proveedorLogic.deleteProveedor(id);

        LOGGER.info("ProveedorResource deleteProveedor: output : void");
    }
    
    /**
     * Elimina el proveedor con el id especificado.
     *
     * @param id el id del proveedor a eliminar.
     * @throws BusinessLogicException Si el proveedor que se desea eliminar no
     * existe
     */
    @DELETE
    @Path("{id:\\d+}/1")
    public void delete2Proveedor(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorResource deleteProveedor: input{0}", id);
        if (proveedorLogic.getProveedor(id) == null) {
            throw new WebApplicationException(A1 + id + A2, 404);
        }
        proveedorLogic.delete2Proveedor(id);

        LOGGER.info("ProveedorResource deleteProveedor: output : void");
    }

    /**
     * Modificar la información de un proveedor.
     *
     * @param id El id del proveedor a modificar
     * @param proveedor La información nueva que se le insertará al
     * proveedor(id)
     * @return el detalle del proveedor con su nueva informacion
     */
    @PUT
    @Path("{id:\\d+}")
    public ProveedorDetailDTO updateProveedor(@PathParam("id") Long id, ProveedorDTO proveedor) {
        LOGGER.log(Level.INFO, "ProveedorResource updateProveedor: input: id:{0} , proveedor: {1}", new Object[]{id, proveedor});

        proveedor.setId(id);
        if (proveedorLogic.getProveedor(id) == null) {
            throw new WebApplicationException(A1 + id + A2, 404);
        }

        ProveedorDetailDTO proDetailDTO = new ProveedorDetailDTO(proveedorLogic.updateProveedor(id, proveedor.toEntity()));

        LOGGER.log(Level.INFO, "ProveedorResource updateProveedor: output: {0}", proDetailDTO);

        return proDetailDTO;

    }

    /**
     * Redirige al recurso transaccion proveedor.
     *
     * @param provId El proveedor
     * @return La clase que contiene las transacciones del proveedor
     * especificado
     */
    @Path("{id: \\d+}/transaccionProveedor")
    public Class<TransaccionProveedorResource> getTransaccionProveedorResource(@PathParam("id") Long provId) {
        if (proveedorLogic.getProveedor(provId) == null) {
            throw new WebApplicationException(A1 + provId + "/transaccionProveedor no existe.", 404);
        }
        return TransaccionProveedorResource.class;
    }

    /**
     * Convierte al entity del proveedor en un detaildto.
     *
     * @param entityList la lista a convetir
     * @return La lista de detaildto del proveedor.
     */
    private List<ProveedorDetailDTO> listEntity2DetailDTO(List<ProveedorEntity> entityList) {
        List<ProveedorDetailDTO> list = new ArrayList<>();
        for (ProveedorEntity entity : entityList) {
            list.add(new ProveedorDetailDTO(entity));
        }
        return list;
    }

}
