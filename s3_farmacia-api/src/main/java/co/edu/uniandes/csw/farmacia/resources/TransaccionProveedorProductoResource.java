/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jd.florezg1
 */
@Path("transaccionProveedor/{transaccionProveedorId:\\d+}/productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionProveedorProductoResource {
        private static final Logger LOGGER = Logger.getLogger(TransaccionProveedorProductoResource.class.getName());

        @GET
        public List<ProductoDetailDTO> list(
                @PathParam("transaccionProveedorId") Long id) 
                throws BusinessLogicException {
            return new ArrayList<ProductoDetailDTO>();
        }
        
        @POST
        @Path("{productoId:\\d+}")
        public ProductoDetailDTO create(
                @PathParam("transaccionProveedorId") Long id,
                @PathParam("productoId") Long productoId) {
            return null;
        }
        
        @DELETE
        @Path("{productoId:\\d+}")
        public void delete(
                @PathParam("transaccionProveedorId") Long id,
                @PathParam("productoId") Long productoId) {
        }

}