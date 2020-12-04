/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CategoriaServicioFacadeLocal;
import edu.proyectofinal.facade.ServiciosFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.CategoriaServicio;
import edu.proyectofinal.modelo.Servicios;
import edu.proyectofinal.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Laura Ballen
 */
@Named(value = "servicioView")
@ViewScoped
public class ServicioView implements Serializable {


    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios = new Servicios();
    private ArrayList<Servicios> listaservicios = new ArrayList<>();

    @EJB
    CategoriaServicioFacadeLocal categoriaServicioFacadeLocal;
    private CategoriaServicio categoriaServicio1 = new CategoriaServicio();
    private ArrayList<CategoriaServicio> listacategoraservicio = new ArrayList<>();

    public ServicioView() {
    }

    @PostConstruct
    public void cargadatos() {

        listaservicios.addAll(serviciosFacadeLocal.findAll());
        listacategoraservicio.addAll(categoriaServicioFacadeLocal.findAll());

        servicios.setCategoriaservicioidCategoriaServicio(new CategoriaServicio());

    }
    
        public void crearServicio() {         
        try {
            
            servicios.setCategoriaservicioidCategoriaServicio(categoriaServicioFacadeLocal.find(servicios.getCategoriaservicioidCategoriaServicio().getIdCategoriaServicio()));
            
            serviciosFacadeLocal.create(servicios);
            listaservicios.add(servicios);
            
        } catch (Exception e) {
            //System.out.println("Error al regustrar" + getClientedocu() + e);
            System.out.println("Erro"+ e );
        }
        servicios= new Servicios();   
    }   
        
        public void cargaDatos(Servicios objser){
        this.servicios=objser;
        }
        

        public void editarServicio() {
        String mensajeSw = "";
        try {
            servicios.setCategoriaservicioidCategoriaServicio(categoriaServicioFacadeLocal.find(servicios.getCategoriaservicioidCategoriaServicio().getIdCategoriaServicio()));
           
            serviciosFacadeLocal.edit(servicios);
            listaservicios.clear();
            listaservicios.addAll(serviciosFacadeLocal.findAll());

            mensajeSw = "swal('Usuario modificado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas modificando' , ' al usuario  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
        
        
        public void removerServicio(Servicios serRe) {
        String mensajeSw = " ";
        try {
            
             listaservicios.remove(serRe);
             serviciosFacadeLocal.remove(serRe);
           
            
            mensajeSw = "swal('Servicio removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas removiendo' , ' al servicio  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
        
        
        
        
    public ArrayList<CategoriaServicio> getListacategoraservicio() {
        return listacategoraservicio;
    }

    public void setListacategoraservicio(ArrayList<CategoriaServicio> listacategoraservicio) {
        this.listacategoraservicio = listacategoraservicio;
    }

    public CategoriaServicio getCategoriaServicio1() {
        return categoriaServicio1;
    }

    public void setCategoriaServicio1(CategoriaServicio categoriaServicio1) {
        this.categoriaServicio1 = categoriaServicio1;
    }

    public ArrayList<Servicios> getListaservicios() {
        return listaservicios;
    }

    public void setListaservicios(ArrayList<Servicios> listaservicios) {
        this.listaservicios = listaservicios;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }
        


    
}
