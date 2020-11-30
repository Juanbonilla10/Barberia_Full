/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.utilidades.Email;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Brayan Algecira
 */
@Named(value = "ordenCompraRequest")
@RequestScoped
public class OrdenCompraRequest implements Serializable {

   private String correoing = "";
   private String mensaje = "";
   private String usuario = "";
   private String codigo = "";
   private String cantidad = "";
   
    
    public OrdenCompraRequest()  {
    }
    
    public void enviarOrden(){
    
        try{
            Email.sendord(getCorreoing(), getMensaje(),getCantidad(),getCodigo() ,getUsuario());
            
        }catch (Exception e){ 
            System.out.println("No se envia" +e.getMessage());
        }
        
    
    }

    public String getCorreoing() {
        return correoing;
    }

    public void setCorreoing(String correoing) {
        this.correoing = correoing;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
