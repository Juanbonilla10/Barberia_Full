/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.Citas;
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
public class CitasFacade extends AbstractFacade<Citas> implements CitasFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitasFacade() {
        super(Citas.class);
    }
    
    @Override
    public Citas eliminarDatos(Integer citasid){
        
        try {
            Query ct = em.createQuery("DELETE FROM Citas c WHERE c.idCitas = :citasid");
            ct.setParameter("citasid", citasid).executeUpdate();
            return new Citas();
        } catch (Exception e) {
            System.out.println("Error al eliminar desde el facade: " + e.getMessage());
            return new Citas();
        }
        
    }
    
    @Override
    public List<Citas> listaCitasPer(int usuariosidUsuarios){
        try {
            Query yt = em.createQuery("SELECT p FROM Citas p WHERE p.usuariosidUsuarios.numIdentificacion = :usuariosidUsuarios");
            yt.setParameter("usuariosidUsuarios", usuariosidUsuarios);
            return yt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.webapp1966781a.facade.ProductoFacade.listaPorCategoria() " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
