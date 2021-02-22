/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CrearProducto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author soporte
 */
@Stateless
public class CrearProductoFacade extends AbstractFacade<CrearProducto> implements CrearProductoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrearProductoFacade() {
        super(CrearProducto.class);
    }
    
    @Override
    public CrearProducto ingreso(int idingresi,int ingreso){
        try {
           Query qm = em.createStoredProcedureQuery("call sp_consultaCantdad(idingresi,ingreso)");
           qm.setParameter("idingresi",idingresi);
           qm.setParameter("ingreso",ingreso);
           return new CrearProducto();
        } catch (Exception e) {
            System.out.println("Error al ingresar mas productos" + e.getMessage());
            return new CrearProducto();
        }
    }
    
}
