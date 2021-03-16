/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.VentaProducto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author soporte
 */
@Stateless
public class VentaProductoFacade extends AbstractFacade<VentaProducto> implements VentaProductoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaProductoFacade() {
        super(VentaProducto.class);
    }
    
    
    @Override
    public VentaProducto eliminarPorId(Integer numeroid){
        try {
            Query di = em.createQuery("DELETE FROM VentaProducto j WHERE j.idventaProducto = :numeroid");
            di.setParameter("numeroid", numeroid).executeUpdate();
            return new VentaProducto();
        } catch (Exception e) {
            System.out.println("Error al realizar sentencia" + e);
            return new VentaProducto();
        }
    }
    
    /**
     *
     * @param descripcionProducto
     * @return
     */
   
    @Override
    public int validaStock(int descripcionProducto){
        try {
            Query ex = em.createQuery("select u.cantidad from CrearProducto u where u.idCrearProducto = :descripcionProducto");
            ex.setParameter("descripcionProducto", descripcionProducto);
            return   (int) ex.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return 7;
        }
    }
    
}
