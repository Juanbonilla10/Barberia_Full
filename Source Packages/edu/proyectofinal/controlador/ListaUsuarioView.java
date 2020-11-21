/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;


import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Usuarios;
import edu.proyectofinal.utilidades.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Laura Ballen
 */
@Named(value = "listaUsuarioView")
@ViewScoped
public class ListaUsuarioView implements Serializable {

    /**
     * Creates a new instance of ListaUsuarioView
     */
    public ListaUsuarioView() {
    }
    
    @EJB
    UsuariosFacadeLocal usuarioFacadeLocal;
    
    private ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    
    private Usuarios usReg = new Usuarios();

    /**
     * Creates a new instance of AdministradorView
     */
    @PostConstruct
    public void leerListaUsuarios() {
        try {
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        
    }
    
    public void cargaUsuarioEditar(Usuarios usuEditar) {
        this.usReg = usuEditar;
    }
    
    public void removerUsuario(Usuarios usuRem) {
        String mensajeSw = " ";
        try {
            usuarioFacadeLocal.remove(usuRem);
            listaUsuarios.remove(usuRem);
            mensajeSw = "swal('Usuario removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas removiendo' , ' al usuario  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void registrarUser() {
        String mensajeSw = "";
        try {
            usReg.setFechaNacimiento("2020");
            usuarioFacadeLocal.create(usReg);
            listaUsuarios.add(usReg);
            mensajeSw = "swal('Usuario registrado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , ' Ya se encuentra registrado  ', 'error')";
            System.out.println("Error" + e);
        }
        usReg = new Usuarios();
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    
    public void editarUsuario() {
        String mensajeSw = "";
        try {
            usuarioFacadeLocal.edit(usReg);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            mensajeSw = "swal('Usuario modificado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas modificando' , ' al usuario  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void correoMasivo(){
        try {
            for (Usuarios lUsuario : listaUsuarios) {
                Email.sendBienvenido(lUsuario.getEmail(), 
                        lUsuario.getNombres() + " " +lUsuario.getApellidos(),
                        lUsuario.getEmail(), lUsuario.getContrasena());
            }
        } catch (Exception e) {
        }
        
    }
    
    
    public ArrayList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuarios getUsReg() {
        return usReg;
    }

    public void setUsReg(Usuarios usReg) {
        this.usReg = usReg;
    }
    
    
}
