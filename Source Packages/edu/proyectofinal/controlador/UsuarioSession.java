/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Laura Ballen
 */
@Named(value = "usuarioSession")
@SessionScoped
public class UsuarioSession implements Serializable {

    @EJB
    UsuariosFacadeLocal UsuariosFacadeLocal;

    private String correoIn = "";
    private String contrasenaIn = "";
    private int cedula;
    private Usuarios usuLogin = new Usuarios();

    /**
     * Creates a new instance of UsuarioSession
     */
    public UsuarioSession() {
    }

    public void inicioSesion() {
        String mensajeSw = " ";

        try {
            usuLogin = UsuariosFacadeLocal.loginUsuario(correoIn, contrasenaIn);
            FacesContext fc = FacesContext.getCurrentInstance();
            switch (usuLogin.getRolidURol().getDescripcion()) {
                case "Administrador":
                    fc.getExternalContext().redirect("Inventario/index.xhtml");
                    cedula = usuLogin.getNumIdentificacion();
                    System.out.println("El id seleccionado es:" + usuLogin.getRolidURol().getDescripcion());
                    break;
                case "Cliente":
                    fc.getExternalContext().redirect("ClienteUsuario/index.xhtml");
                    cedula = usuLogin.getNumIdentificacion();
                    System.out.println("El id seleccionado es:" + usuLogin.getRolidURol().getDescripcion() + " y el numero de cedula es: " + this.cedula);
                    break;
                case "Barbero":
                    fc.getExternalContext().redirect("BarberoUsuario/index.xhtml");
                    cedula = usuLogin.getNumIdentificacion();
                    System.out.println("El id seleccionado es:" + usuLogin.getRolidURol().getDescripcion() + " y el numero de cedula es: " + this.cedula);
                    break;
                case "Auxiliar":
                    fc.getExternalContext().redirect("Auxiliar/index.xhtml");
                    cedula = usuLogin.getNumIdentificacion();
                    System.out.println("El id seleccionado es:" + usuLogin.getRolidURol().getDescripcion());
                    break;
                default:
                    System.out.println("Error ninguno fue");
            }

            /*if (usuLogin.getIdUsuarios() == null) {
                mensajeSw = "swal('El usuario' , ' No se encuentra registrado  ', 'error')";

            } else {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("Inventario/index.xhtml");

            }*/
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , ' No se encuentra registrado  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getContrasenaIn() {
        return contrasenaIn;
    }

    public void setContrasenaIn(String contrasenaIn) {
        this.contrasenaIn = contrasenaIn;
    }

    public Usuarios getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuarios usuLogin) {
        this.usuLogin = usuLogin;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

}
