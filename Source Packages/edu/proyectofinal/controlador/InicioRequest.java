/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.RolFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Rol;
import edu.proyectofinal.modelo.Usuarios;
import edu.proyectofinal.utilidades.Email;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author soporte
 */
@Named(value = "inicioRequest")
@RequestScoped
public class InicioRequest implements Serializable {
    
    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    
    @EJB
    RolFacadeLocal rolFacadeLocal;
    private Rol rol = new Rol();
     
    private Usuarios objusu = new Usuarios();
    private String correoing = "";
    private String correoIn = "";
    private String contrasenaIn = ""; 
    private Integer idRol=2;
            /**
     * Creates a new instance of InicioRequest
     */
    
    public void registrarUser(){
        try {
            objusu.setFechaNacimiento("2020");
            usuariosFacadeLocal.create(objusu);
        } catch (Exception e) {
            System.out.println("No registrado" + e);
        }
    }
    
    public void registrarUserFuera(){
        try {
            objusu.setFechaNacimiento("2020");
            objusu.setRolidURol(rolFacadeLocal.find(idRol));
            usuariosFacadeLocal.create(objusu);
        } catch (Exception e) {
            System.out.println("No registrado" + e);
        }
    }
    public void restaurarContraseña(){
        Usuarios usrge = new Usuarios();
        String mensaje = "";
        try {
            usrge = usuariosFacadeLocal.restauPass(correoing);
            if (usrge.getEmail()== null){
                mensaje = "swal.fire('El correo', 'No esta registrado','error')";
                
            } 
            else {
                Email.sendClaves(usrge.getEmail(), usrge.getNombres() + " " + usrge.getApellidos(), correoing, usrge.getContrasena());
                
                mensaje="swal.fire('Tu contraseña se','ha enviado al correo','success')";
            }
            System.out.println("USUARIO EXISTENTE");
        } catch (Exception e){
            mensaje="swal.fire('El correo','No se encuentra','error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    
    public void ventas(){
    
    }
    
    public InicioRequest() {
    }

    public Usuarios getObjusu() {
        return objusu;
    }

    public void setObjusu(Usuarios objusu) {
        this.objusu = objusu;
    }

    public String getCorreoing() {
        return correoing;
    }

    public void setCorreoing(String correoing) {
        this.correoing = correoing;
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

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
}
