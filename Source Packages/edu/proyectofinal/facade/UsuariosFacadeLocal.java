/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();

    public Usuarios loginUsuario(String email, String contrasena);

    public Usuarios restauPass(String correo);
    
}
