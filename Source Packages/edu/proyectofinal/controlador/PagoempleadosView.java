/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.PagoServicioFacadeLocal;
import edu.proyectofinal.facade.ServiciosFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.PagoServicio;
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
@Named(value = "pagoempleadosView")
@ViewScoped
public class PagoempleadosView implements Serializable {
    

    @EJB
    PagoServicioFacadeLocal pagoServicioFacadeLocal;
    private PagoServicio objpago = new PagoServicio();
    private ArrayList<PagoServicio> listapagoservicios = new ArrayList<>();

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private Usuarios objusu = new Usuarios();
    private ArrayList<Usuarios> listausuario = new ArrayList<>();

    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios = new Servicios();
    private ArrayList<Servicios> listaservicios = new ArrayList<>();

    public PagoempleadosView() {
    }

    @PostConstruct
    public void cargadatos() {

        listausuario.addAll(usuariosFacadeLocal.findAll());
        listaservicios.addAll(serviciosFacadeLocal.findAll());
        listapagoservicios.addAll(pagoServicioFacadeLocal.findAll());

        objpago.setServiciosIdservicio(new Servicios());
        objpago.setUsuariosidUsuarios(new Usuarios());
    }

    public void crearPago() {
        try {

            objpago.setServiciosIdservicio(serviciosFacadeLocal.find(objpago.getServiciosIdservicio().getIdservicio()));
            objpago.setUsuariosidUsuarios(usuariosFacadeLocal.find(objpago.getUsuariosidUsuarios().getIdUsuarios()));

            pagoServicioFacadeLocal.create(objpago);
            listapagoservicios.add(objpago);
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        objpago = new PagoServicio();

    }

    public void cargaDatos(PagoServicio pagoS) {
        this.objpago = pagoS;
    }

    public void removerPago(PagoServicio pagRe) {
        String mensajeSw = " ";
        try {

            pagoServicioFacadeLocal.remove(pagRe);
            listapagoservicios.remove(pagRe);
            

            mensajeSw = "swal('Pago removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas removiendo' , ' el pago ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void editarPago() {
        String mensajeSw = "";
        try {
            
            
            objpago.setServiciosIdservicio(serviciosFacadeLocal.find(objpago.getServiciosIdservicio().getIdservicio()));
            objpago.setUsuariosidUsuarios(usuariosFacadeLocal.find(objpago.getUsuariosidUsuarios().getIdUsuarios()));

            pagoServicioFacadeLocal.edit(objpago);
            listapagoservicios.clear();
            listapagoservicios.addAll(pagoServicioFacadeLocal.findAll());
            

            mensajeSw = "swal('pago modificado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas modificando' , ' el pago  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    

    
    
    public PagoServicio getObjpago() {
        return objpago;
    }

    public void setObjpago(PagoServicio objpago) {
        this.objpago = objpago;
    }

    public ArrayList<PagoServicio> getListapagoservicios() {
        return listapagoservicios;
    }

    public void setListapagoservicios(ArrayList<PagoServicio> listapagoservicios) {
        this.listapagoservicios = listapagoservicios;
    }

    public Usuarios getObjusu() {
        return objusu;
    }

    public void setObjusu(Usuarios objusu) {
        this.objusu = objusu;
    }

    public ArrayList<Usuarios> getListausuario() {
        return listausuario;
    }

    public void setListausuario(ArrayList<Usuarios> listausuario) {
        this.listausuario = listausuario;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public ArrayList<Servicios> getListaservicios() {
        return listaservicios;
    }

    public void setListaservicios(ArrayList<Servicios> listaservicios) {
        this.listaservicios = listaservicios;
    }

}