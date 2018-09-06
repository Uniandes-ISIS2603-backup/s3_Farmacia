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

/**
 *
 * @author estudiante
 */@Stateless
public class ProductoPersistence {
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext (unitName = "DrugsHousePU")
    protected EntityManager em;
    
    public ProductoEntity create(ProductoEntity productoEntity) {
        em.persist(productoEntity);
        return productoEntity;
    }
    
    
    public List<ProductoEntity> list() {
        return em
                .createStoredProcedureQuery("SELECT * FROM ProductoEntity", 
                        ProductoEntity.class)
                .getResultList();
    }
    
    public ProductoEntity find(Long id) {
        return em.find(ProductoEntity.class, id);
    }
    
    public ProductoEntity update(ProductoEntity producto) {
        em.merge(producto);
        return producto;
    }
    
    public void delete(Long id) {
        em.remove(em.find(ProductoEntity.class, id));
    }
    
    public void getByName(String name) {
        //TypedQuery query = em.createQuery("select producto", type)
        //return em.find(Producto.class, );
    }
}
