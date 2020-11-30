/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.Cronograma;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author soporte
 */
@Stateless
public class CronogramaFacade extends AbstractFacade<Cronograma> implements CronogramaFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CronogramaFacade() {
        super(Cronograma.class);
    }
    
  
    public Cronograma eliminarDatos(Integer cronogramaid){
        
        try {
            Query cg = em.createQuery("DELETE FROM Cronograma c WHERE c.idCronograma = :cronogramaid");
            cg.setParameter("cronogramaid", cronogramaid).executeUpdate();
            return new Cronograma();
        } catch (Exception e) {
            System.out.println("Error al eliminar desde el facade: " + e.getMessage());
            return new Cronograma();
        }
        
    }
    
}
