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
    public ProductoDetailDTO getProducto(@PathParam("id") Long proveedorId , @PathParam("productoId") Long productoId ) throws BusinessLogicException 
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
   public List<ProductoDetailDTO> getProductos(@PathParam("id") Long proveedorId) 
   {
       LOGGER.log(Level.INFO, "ProveedorProductosResource getProductos: input: {0}", proveedorId);
      List<ProductoDetailDTO> lista = productListEntityToDTO(proveedorProductosLogic.getProductos(proveedorId));

       LOGGER.log(Level.INFO, "ProveedorProductosResource getProductos: output: {0}", lista.toString());
         return lista;
   }
    @POST
   @Path("{productoId \\ d+ }")
    public ProductoDetailDTO addProducto(@PathParam("id") Long proveedorId , @PathParam("productoId") Long productoId)
    {
        LOGGER.log(Level.INFO,"ProveedorProductosResource addProducto: input: proveedorId {0} , productoId {1} ",new Object[]{proveedorId, productoId});
       //  if (productoLogic.getProducto(productoId) == null) {
       //     throw new WebApplicationException("El recurso /producto/" + productoId + " no existe.", 404);
      //   }
      return null;
    }
   // @PUT
   // public List<ProductoDetailDTO> replaceProductos(@PathParam("id") Long proveedorId, List<ProductoDetailDTO> producto)
    {
         LOGGER.log(Level.INFO, "ProveedorProductosResource replaceProductos: input: proveedorId {0} , producots {1}", new Object[]{proveedorId, producto.toString()});
        for (ProductoDetailDTO produ : producs) {
            if (productoLogic.getProducto(produ.getId()) == null) {
                throw new WebApplicationException("El recurso /productos/" + produ.getId() + " no existe.", 404);
            }
        }
        List<ProductoDetailDTO> lista = productsListEntity2DTO(proveedorProductosLogic.replaceProductos(proveedorId, productsListDTO2Entity(producto)));
        LOGGER.log(Level.INFO, "ProveedorProductosResource replaceProductos: output:{0}", lista.toString());
        return lista;
    }
        @DELETE
           @Path("{productoId: \\d+}")
           public void removeProducto(@PathParam("id") Long proveedorId, @PathParam("authorsId") Long productoId) {
               LOGGER.log(Level.INFO, "ProveedorProductosResource removeProducto: input: proveedorId {0} , productoId {1}", new Object[]{proveedorId, productoId});
               if (productoLogic.getProducto(productoId) == null) {
                   throw new WebApplicationException("El recurso /productos/" + productoId + " no existe.", 404);
               }
               proveedorProductos.removeProducto(proveedorId, productoId);
               LOGGER.info("ProveedorProductosResource removeProducto: output: void");
           }
    
     private List<ProductoDetailDTO> productListEntityToDTO(List <ProductoEntity> entityList)
    {
        List<ProductoDetailDTO> list = new ArrayList<>();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDetailDTO(entity));
        }
        return list;
    }
    
    //private List<ProductoEntity> productListDTOToEntity(List <ProductoDetailDTO> dtoList)
    {
        List<ProductoEntity> list = new ArrayList<>();
        for (ProductoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
*/
}
