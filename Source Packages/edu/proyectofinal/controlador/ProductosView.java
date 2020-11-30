/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CrearProductoFacadeLocal;
import edu.proyectofinal.facade.ProveedorFacadeLocal;
import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.Proveedor;
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
@Named(value = "productosView")
@ViewScoped
public class ProductosView implements Serializable {

   
    
     @EJB
     CrearProductoFacadeLocal crearProductoFacadeLocal;
     private ArrayList<CrearProducto> listaCrearProducto = new ArrayList<>();
     private CrearProducto objcrpro = new CrearProducto();
     
     @EJB
     ProveedorFacadeLocal proveedorFacadeLocal;
     private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
     private Proveedor objprov = new Proveedor(); 
     
     public ProductosView() {
    }
   
    @PostConstruct
    public void listprod(){
    listaproveedor.addAll(proveedorFacadeLocal.findAll());
    listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
    
    objcrpro.setProveedorIdProveedor(new Proveedor());
    
    }
     
    public void registraProd(){
       try {
            
            crearProductoFacadeLocal.create(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("No registrado busque mas opciones" + e);
        }
        
        objcrpro = new CrearProducto();
    }
    public void retornaProducto(CrearProducto objpros){
        this.objcrpro = objpros;       
    }
     
          public void actualizarProv(){
        try {
            crearProductoFacadeLocal.edit(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
    }
      
       
       public void eliminarprod(CrearProducto prorem){
          try {
     crearProductoFacadeLocal.remove(objcrpro);
     listaCrearProducto.remove(objcrpro);
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

    public ArrayList<CrearProducto> getListaCrearProducto() {
        return listaCrearProducto;
    }

    public void setListaCrearProducto(ArrayList<CrearProducto> listaCrearProducto) {
        this.listaCrearProducto = listaCrearProducto;
    }

    public CrearProducto getObjcrpro() {
        return objcrpro;
    }

    public void setObjcrpro(CrearProducto objcrpro) {
        this.objcrpro = objcrpro;
    }
    
    }


    
