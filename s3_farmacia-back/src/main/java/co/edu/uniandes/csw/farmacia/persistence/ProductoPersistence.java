/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */@Stateless
public class ProductoPersistence {
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    
    /**
     * Almacena una nueva instancia de un producto
     * @param productoEntity instancia a almacenar
     * @return instancia enviada por parametro
     */
    public ProductoEntity create(ProductoEntity productoEntity) {
        em.persist(productoEntity);
        return productoEntity;
    }
    
    /**
     * Obtiene los productos almacenados en la base de datos
     * @return lista de productos
     */
    public List<ProductoEntity> list() {
        return em
                .createQuery("select e from ProductoEntity e", 
                        ProductoEntity.class)
                .getResultList();
    }
    
    /**
     * Retorna el producto con el id dado, null en caso de que no exista
     * @param id del producto
     * @return producto con el id, null si no se encontró
     */
    public ProductoEntity find(Long id) {
        return em.find(ProductoEntity.class, id);
    }
    
    /**
     * Actualiza el producto con los datos ingresados por parámetro
     * @param producto producto actualizado
     * @return Producto actualizado
     */
    public ProductoEntity update(ProductoEntity producto) {
        return em.merge(producto);
    }
    
    /**
     * Elimina el elemento con el id dado por parametro
     * @param id id del elemento a eliminar
     */
    public void delete(Long id) {
        em.remove(em.find(ProductoEntity.class, id));
    }
}
