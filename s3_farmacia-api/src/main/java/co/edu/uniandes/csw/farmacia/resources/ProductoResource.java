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
import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProductoLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Path("productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProductoResource {

    private static final Logger LOGGER = Logger.getLogger(
            ClienteResource.class.getName());

    private static final String P1 = " El recurso /clientes/ ";

    private static final String P2 = " no existe.";

    @Inject
    private ProductoLogic logic;

    @Path("registro/{registroId: \\d+}")
    @POST
    public ProductoDTO createProducto(@PathParam("registroId") Long registroId,
            ProductoDTO producto)
            throws WebApplicationException {
        if (producto == null) {
            throw new WebApplicationException(
                    "El nuevo producto no puede ser nulo", 400);
        }
        try {
            ProductoEntity productoEntity = producto.toEntity();
            ProductoEntity result = logic.create(productoEntity, registroId);
            return new ProductoDTO(result);
        } catch (BusinessLogicException ble) {
            throw new WebApplicationException(ble.getMessage(), 400);
        }

    }

    @Path("{productoId: \\d+}/asociate/registro/{registroId: \\d+}")
    @POST
    public void asociateProductoRegistro(@PathParam("productoId") Long productoId,
            @PathParam("registroId") Long registroId)
            throws WebApplicationException {
        try {
            logic.asociate(productoId, registroId);
        } catch (BusinessLogicException ble) {
            if (ble.getMessage()
                    .equals("El registro ya tiene un producto asociado")) {
                throw new WebApplicationException(ble.getMessage(), 400);
            } else {
                throw new WebApplicationException(ble.getMessage(), 404);
            }
        }
    }

    @GET
    public List<ProductoDetailDTO> getProductos() {
        List<ProductoEntity> productos = logic.list();
        List<ProductoDetailDTO> list = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            //list[i] = new ProductoDTO(productos.get(i));
            list.add(new ProductoDetailDTO(productos.get(i)));
        }
        LOGGER.log(Level.INFO, "ProveedorResource getProveedores: output : {0}", list);
        return list;
    }

    @GET
    @Path("{productosId:\\d+}")
    public ProductoDetailDTO getProducto(@PathParam("productosId") Long id) throws
            WebApplicationException {
        try {
            ProductoEntity producto = logic.get(id);
            return new ProductoDetailDTO(producto);
        } catch (BusinessLogicException ble) {
            throw new WebApplicationException(
                    P1 + id + P2,
                    404);
        }
    }

    @DELETE
    @Path("{productosId:\\d+}")
    public void deleteProducto(@PathParam("productosId") Long id) throws
            WebApplicationException {
        try {
            logic.delete(id);
        } catch (BusinessLogicException ble) {
            throw new WebApplicationException(
                    P1 + id + P2, 404);
        }
    }

    @PUT
    @Path("{productosId: \\d+}")
    public ProductoDTO refreshDataProducto(@PathParam("productosId") Long productoId,
            ProductoDTO producto) throws WebApplicationException {

        if (producto == null) {
            throw new WebApplicationException(
                    "El nuevo producto no puede ser nulo", 400);
        }
        try {
            producto.setId(productoId);
            ProductoEntity productoEntity = producto.toEntity();
            return new ProductoDTO(logic.update(productoId, productoEntity));
        } catch (BusinessLogicException ex) {
            if (ex.getMessage()
                    .equals("No se encontró el elemento a actualizar")) {
                throw new WebApplicationException(
                        P1 + productoId + P2, 404);
            } else {
                throw new WebApplicationException(
                        ex.getMessage(), 400);
            }
        }
    }
}
