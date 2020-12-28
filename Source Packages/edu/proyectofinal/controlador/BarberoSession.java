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
import edu.proyectofinal.modelo.TipoPago;
import edu.proyectofinal.modelo.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Laura Ballen
 */
@Named(value = "barberoSession")
@SessionScoped
public class BarberoSession implements Serializable {


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

    @Inject
    UsuarioSession usuarioSession;

    @PostConstruct
    public void cargaRolBarbero() {
        try {
            listausuario.addAll(usuariosFacadeLocal.findAll());
            listaservicios.addAll(serviciosFacadeLocal.findAll());
            listapagoservicios.addAll(pagoServicioFacadeLocal.listaPagoB(usuarioSession.getCedula()));

            objpago.setServiciosIdservicio(new Servicios());
            objpago.setUsuariosidUsuarios(new Usuarios());

            System.out.println("CEDULA : " + usuarioSession.getCedula());
        } catch (Exception e) {
            System.out.println("edu.webapp1966781a.controlador.ProductosView.cargaCategorias() " + e.getMessage());
        }
    }

    
    
    public BarberoSession() {
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
