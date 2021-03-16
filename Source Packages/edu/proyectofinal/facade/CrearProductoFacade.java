/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CrearProducto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

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
           StoredProcedureQuery qm = em.createStoredProcedureQuery("sp_consultaCantdad");
           qm.registerStoredProcedureParameter("idingresi", String.class , ParameterMode.IN);
           qm.registerStoredProcedureParameter("ingreso", String.class , ParameterMode.IN);
           qm.registerStoredProcedureParameter("salida", Long.class, ParameterMode.OUT);
           qm.setParameter("idingresi", idingresi);
           qm.setParameter("ingreso", ingreso);
           qm.execute();
           return (CrearProducto) qm.getOutputParameterValue("salida");      
        } catch (Exception e) {
            System.out.println("Error al ingresar mas productos" + e.getMessage());
            return new CrearProducto();
        }
    }
    
}
