/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CitasFacadeLocal;
import edu.proyectofinal.facade.ServiciosFacadeLocal;
import edu.proyectofinal.facade.TipoPagoFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Citas;
import edu.proyectofinal.modelo.Servicios;
import edu.proyectofinal.modelo.TipoPago;
import edu.proyectofinal.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author soporte
 */
@Named(value = "citaView")
@ViewScoped
public class CitaView implements Serializable{
    
    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private Usuarios usuarios = new Usuarios();
    private ArrayList<Usuarios> listauser = new ArrayList<>();
    
    @EJB
    TipoPagoFacadeLocal tipoPagoFacadeLocal;
    private TipoPago tipoPago = new TipoPago();
    private ArrayList<TipoPago> listapagos = new ArrayList<>();
    
    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios = new Servicios();
    private ArrayList<Servicios> listaservicios = new ArrayList<>();
    
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    private Citas citas = new Citas();
    private ArrayList<Citas> listacitas = new ArrayList<>();
    
    
    
    /**
     * Creates a new instance of CitaView
     */
    public CitaView() {
    }
    
    @PostConstruct
    public void cargadatos(){
        listauser.addAll(usuariosFacadeLocal.findAll());
        listaservicios.addAll(serviciosFacadeLocal.findAll());
        listapagos.addAll(tipoPagoFacadeLocal.findAll());
        
        citas.setServiciosIdservicio(new Servicios());
        citas.setUsuariosidUsuarios(new Usuarios());
        citas.setTipopagoidTipoPago(new TipoPago());
        
    }
    
    public void crearCita(){
        try {
            
            citas.setServiciosIdservicio(serviciosFacadeLocal.find(citas.getServiciosIdservicio().getIdservicio()));
            citas.setUsuariosidUsuarios(usuariosFacadeLocal.find(citas.getUsuariosidUsuarios().getIdUsuarios()));
            citas.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(citas.getTipopagoidTipoPago().getIdTipoPago()));
            
            citasFacadeLocal.create(citas);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuarios> getListauser() {
        return listauser;
    }

    public void setListauser(ArrayList<Usuarios> listauser) {
        this.listauser = listauser;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<TipoPago> getListapagos() {
        return listapagos;
    }

    public void setListapagos(ArrayList<TipoPago> listapagos) {
        this.listapagos = listapagos;
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

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }

    public ArrayList<Citas> getListacitas() {
        return listacitas;
    }

    public void setListacitas(ArrayList<Citas> listacitas) {
        this.listacitas = listacitas;
    }
    
}
