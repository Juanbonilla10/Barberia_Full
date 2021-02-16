/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.HorariosCronograma;
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
public class HorariosCronogramaFacade extends AbstractFacade<HorariosCronograma> implements HorariosCronogramaFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorariosCronogramaFacade() {
        super(HorariosCronograma.class);
    }
    
    @Override
    public List<HorariosCronograma> listardisponibles(String horadis){
        try {
            Query hd = em.createQuery("SELECT U FROM HorariosCronograma U where U.status =:horadis");
            hd.setParameter("horadis", horadis);
            return hd.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public HorariosCronograma actualizaEstadoCita(String horaingreso){
        try {
            Query aec = em.createQuery("UPDATE HorariosCronograma p SET p.status='1' WHERE p.hora=:horaingreso");
            aec.setParameter("horaingreso", horaingreso).executeUpdate();
            return new HorariosCronograma();
        } catch (Exception e) {
            System.out.println("Error al actualizar cita" + e.getMessage());
            return new HorariosCronograma();
        }
    }
    
}
