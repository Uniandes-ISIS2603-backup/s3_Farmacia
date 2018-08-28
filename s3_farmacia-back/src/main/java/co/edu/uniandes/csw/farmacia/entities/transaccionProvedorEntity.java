/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *Clase que contiene la entidad de transaccionProvedor
 * @author ra.ariasr
 */
public class transaccionProvedorEntity extends BaseEntity implements Serializable
{
    /**
     * atributo que hace referencia al timepo empleado en la transaccion
     */
    private String tiempo;
    
    /**
     * atributo que hace referencua al monto empleado en la transaccion
     */
    private Double monto;
    
    private static final Logger LOGGER = Logger.getLogger(transaccionProvedorEntity.class.getName());
    
    public void setTiempo(String pTiempo)
    {
        tiempo= pTiempo;
    }
    public String getTiempo()
    {
        return tiempo;
    }
    
    public void setMonto(Double pMonto)
    {
        monto= pMonto;
    }
    
    public Double getMonto()
    {
        return monto;
    }
    
}
