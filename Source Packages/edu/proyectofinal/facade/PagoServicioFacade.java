/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.PagoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author soporte
 */
@Stateless
public class PagoServicioFacade extends AbstractFacade<PagoServicio> implements PagoServicioFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoServicioFacade() {
        super(PagoServicio.class);
    }
    

    @Override
    public List<PagoServicio> listaPagoB(int usuariosidUsuarios) {
        
        try {
            Query yt = em.createQuery("SELECT p FROM PagoServicio p WHERE p.usuariosidUsuarios.numIdentificacion = :usuariosidUsuarios");
            yt.setParameter("usuariosidUsuarios", usuariosidUsuarios);
            return yt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.webapp1966781a.facade.ProductoFacade.listaPorCategoria() " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
