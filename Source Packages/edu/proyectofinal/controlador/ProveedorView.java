/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.ProveedorFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Proveedor;
import edu.proyectofinal.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Brayan Algecira
 */
@Named(value = "proveedorView")
@ViewScoped
public class ProveedorView implements Serializable{
     
     @EJB
     ProveedorFacadeLocal proveedorFacadeLocal;
     private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
     private Proveedor objprov = new Proveedor(); 
     
      @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private ArrayList<Usuarios> listausuario = new ArrayList<>();
    private Usuarios objusru = new Usuarios();
     
     public ProveedorView()  {
    }
  
   
   
    
     @PostConstruct
     public void listaProv(){
     listaproveedor.addAll(proveedorFacadeLocal.findAll());
     listausuario.addAll (usuariosFacadeLocal.findAll());
     
     objprov.setUsuariosidUsuarios1(new Usuarios());  
     
     }
     
    
    
    public void registrarProv(){
        try {
            
            proveedorFacadeLocal.create(objprov);
            listaproveedor.clear();
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("No registrado busque mas opciones" + e);
        }
        
        objprov = new Proveedor();
    }
    public void retornaDatos(Proveedor objpr){
        this.objprov = objpr;       
    }
     
       public void actualizarProv(){
        try {
            proveedorFacadeLocal.edit(objprov);
            listaproveedor.clear();
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
    }
       
       
     public void eliminarProv(Proveedor prorem){
          try {
     proveedorFacadeLocal.remove(objprov);
     listaproveedor.remove(objprov);
     } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
     }

    public ArrayList<Proveedor> getListaproveedor() {
        return listaproveedor;
    }

    public void setListaproveedor(ArrayList<Proveedor> listaproveedor) {
        this.listaproveedor = listaproveedor;
    }

    public Proveedor getObjprov() {
        return objprov;
    }

    public void setObjprov(Proveedor objprov) {
        this.objprov = objprov;
    }

    public ArrayList<Usuarios> getListausuario() {
        return listausuario;
    }

    public void setListausuario(ArrayList<Usuarios> listausuario) {
        this.listausuario = listausuario;
    }

    public Usuarios getObjusru() {
        return objusru;
    }

    public void setObjusru(Usuarios objusru) {
        this.objusru = objusru;
    }


    
}
