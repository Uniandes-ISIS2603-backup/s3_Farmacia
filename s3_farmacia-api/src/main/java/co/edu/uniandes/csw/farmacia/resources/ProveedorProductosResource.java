              /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorProductosLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author francisco
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorProductosResource
{
    /*
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorProductosResource.class.getName());
    
    @Inject
     private ProveedorProductosLogic proveedorProductosLogic;
    
   // @Inject
     //private ProductoLogic productoLogic;
    
    
    @GET
    @Path("{productoId \\ d+ }")
    public ProductoDetailDTO getProducto(@PathParam("proveedorId") Long proveedorId , @PathParam("productoId") Long productoId ) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ProveedorProductosResource getProducto: input: proveedorId {0} , productoId {1}", new Object[]{proveedorId, productoId});
        //  if(productoLogic.getProducto(productoId)==null)
        //  {
       //      throw new WebApplicationException("El recurso /producto/" + productoId + " no existe.", 404);
        //  }
        ProductoDetailDTO detailDTO = new ProductoDetailDTO(proveedorProductosLogic.getProducto(proveedorId,productoId));
            LOGGER.log(Level.INFO, "BookAuthorsResource getAuthor: output: {0}", detailDTO.toString());
       return detailDTO;
    }
  @GET
   public List<ProductoDetailDTO> getProductos(@PathParam("proveedorId") Long proveedorId) 
   {
       LOGGER.log(Level.INFO, "ProveedorProductosResource getProductos: input: {0}", proveedorId);
      List<ProductoDetailDTO> lista = productListEntityToDTO(proveedorProductosLogic.getProductos(proveedorId));

       LOGGER.log(Level.INFO, "ProveedorProductosResource getProductos: output: {0}", lista.toString());
         return lista;
   }
    @POST
   @Path("{productoId \\ d+ }")
    public ProductoDetailDTO addProducto(@PathParam("proveedorId") Long proveedorId , @PathParam("productoId") Long productoId)
    {
        LOGGER.log(Level.INFO,"ProveedorProductosResource addProducto: input: proveedorId {0} , productoId {1} ",new Object[]{proveedorId, productoId});
       //  if (productoLogic.getProducto(productoId) == null) {
       //     throw new WebApplicationException("El recurso /producto/" + productoId + " no existe.", 404);
      //   }
      return null;
    }
   // @PUT
   // public List<ProductoDetailDTO> replaceProductos(@PathParam("proveedorId") Long proveedorId, List<ProductoDetailDTO> producto){}
  // @DELETE
   //   public void deleteProductos(@PathParam("proveedorId") Long proveedorId) {}
    
     private List<ProductoDetailDTO> productListEntityToDTO(List <ProductoEntity> entityList){return null;}
    
    //private List<ProductoEntity> productListDTOToEntity(List <ProductoDetailDTO> dtoList){}
    
   
*/
}
