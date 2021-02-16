/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    @Override
    public Usuarios restauPass(String correo) {
        try {
            Query qm = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :correo");
            qm.setParameter("correo", correo);
            return (Usuarios) qm.getSingleResult();
        } catch (Exception e) {
            return new Usuarios();
        }
    }
    
 
    @Override
    public Usuarios loginUsuario(String email, String contrasena){
           try {
               Query q = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email AND u.contrasena =:contrasena ");
               q.setParameter("email", email);
               q.setParameter("contrasena", contrasena);
               return (Usuarios) q.getSingleResult();
               
        } catch (Exception e) {
            return new Usuarios();
        }
    }
    
    @Override
    public List<Usuarios> lista(int idrol){
        try{
            Query r = em.createQuery("SELECT u FROM Usuarios u WHERE u.rolidURol.idURol=:idrol");
            r.setParameter("idrol", idrol);
            return r.getResultList();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
    
}
