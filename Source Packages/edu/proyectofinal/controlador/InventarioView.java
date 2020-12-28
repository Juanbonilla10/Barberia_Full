/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CrearProductoFacadeLocal;
import edu.proyectofinal.facade.InventarioFacadeLocal;
import edu.proyectofinal.facade.ProveedorFacadeLocal;
import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.Inventario;
import edu.proyectofinal.modelo.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Brayan Algecira
 */
@Named(value = "inventarioView")
@ViewScoped
public class InventarioView implements Serializable{

  
     @EJB
     CrearProductoFacadeLocal crearProductoFacadeLocal;
   
     @EJB
     InventarioFacadeLocal InventarioFacadeLocal;
   
     
     @EJB
     ProveedorFacadeLocal proveedorFacadeLocal;
     
     private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
     private Proveedor objprov = new Proveedor(); 
     
     private ArrayList<CrearProducto> listapro = new ArrayList<>();
     private CrearProducto objcrpro = new CrearProducto();
    
     
     private ArrayList<Inventario> listaInventario = new ArrayList<>();
     private Inventario objinv = new Inventario();
     
     
    @PostConstruct
    public void cargaProveedor() {
        try {
            objprov = proveedorFacadeLocal.find(1);
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
            objcrpro = crearProductoFacadeLocal.find(1);
            listapro.addAll(crearProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Respuesta" + e.getMessage());
        }
    }
      public InventarioView() {
    }

   
    public void selecionProveedor(int idCategoria) {
        objcrpro = crearProductoFacadeLocal.find(idCategoria);
    }
    
     public void selecionProd(int idCateg) {
        objprov = proveedorFacadeLocal.find(idCateg);
    }
   
   public int catidadProductos() {
        return crearProductoFacadeLocal.count();
    }
   
   
  

 

    public CrearProducto getObjcrpro() {
        return objcrpro;
    }

    public void setObjcrpro(CrearProducto objcrpro) {
        this.objcrpro = objcrpro;
    }

    public ArrayList<Inventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(ArrayList<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public Inventario getObjinv() {
        return objinv;
    }

    public void setObjinv(Inventario objinv) {
        this.objinv = objinv;
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

    public ArrayList<CrearProducto> getListapro() {
        return listapro;
    }

    public void setListapro(ArrayList<CrearProducto> listapro) {
        this.listapro = listapro;
    }

  
}