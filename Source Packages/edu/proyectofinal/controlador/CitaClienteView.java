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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author soporte
 */
@Named(value = "citaClienteView")
@SessionScoped
public class CitaClienteView implements Serializable {

    @EJB
    CitasFacadeLocal citasFacadeLocal;
    private Citas citas = new Citas();
    private ArrayList<Citas> listacitas = new ArrayList<>();

    @EJB
    TipoPagoFacadeLocal tipoPagoFacadeLocal;
    private TipoPago tipoPago = new TipoPago();
    private ArrayList<TipoPago> listapago = new ArrayList<>();

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private Usuarios usuarios = new Usuarios();
    private ArrayList<Usuarios> listausu = new ArrayList<>();

    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios = new Servicios();
    private ArrayList<Servicios> listaservices = new ArrayList<>();

    @Inject
    UsuarioSession usuarioSession;

    @PostConstruct
    public void cargaCategorias() {
        try {
            listausu.addAll(usuariosFacadeLocal.findAll());
            listapago.addAll(tipoPagoFacadeLocal.findAll());
            listaservices.addAll(serviciosFacadeLocal.findAll());
            listacitas.addAll(citasFacadeLocal.listaCitasPer(usuarioSession.getCedula()));
            citas.setServiciosIdservicio(new Servicios());
            citas.setUsuariosidUsuarios(new Usuarios());
            citas.setTipopagoidTipoPago(new TipoPago());
            System.out.println("CEDULA : " + usuarioSession.getCedula());
        } catch (Exception e) {
            System.out.println("edu.webapp1966781a.controlador.ProductosView.cargaCategorias() " + e.getMessage());
        }
    }

    public void selecionCategoria(int idCitas) {
        citas = citasFacadeLocal.find(idCitas);
    }

    public int catidadProductos() {
        return citasFacadeLocal.count();
    }

    public void crearCitaCliente() {

        try {

            citas.setUsuariosidUsuarios(usuariosFacadeLocal.find(usuarioSession.getUsuLogin().getIdUsuarios()));
            citas.setServiciosIdservicio(serviciosFacadeLocal.find(citas.getServiciosIdservicio().getIdservicio()));
            citas.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(citas.getTipopagoidTipoPago().getIdTipoPago()));

            citasFacadeLocal.create(citas);

            listacitas.clear();
            listacitas.addAll(citasFacadeLocal.listaCitasPer(usuarioSession.getCedula()));

            System.out.println("Se insertaron los datos correspondientes");

        } catch (Exception e) {
            System.out.println("Error al crear: " + e.getMessage());
        }

    }

    public void eliminarCitaCli(Citas uscita) {

        try {
            citasFacadeLocal.remove(uscita);
            listacitas.clear();
            listacitas.addAll(citasFacadeLocal.listaCitasPer(usuarioSession.getCedula()));
            System.out.println("Creado correctamente : ");
        } catch (Exception e) {
            System.out.println("Error al eliminar datos: " + e.getMessage());
        }

    }

    public void cargadatoscita(Citas citap) {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("index_cita_update.xhtml");
            this.citas = citap;
            //../ClienteUsuario/index.xhtml
        } catch (Exception e) {
            System.out.println("Error al cargardatos: " + e);
        }

    }

    public void actualizarCitaCli() {
        try {
            citas.setUsuariosidUsuarios(usuariosFacadeLocal.find(usuarioSession.getUsuLogin().getIdUsuarios()));
            citas.setServiciosIdservicio(serviciosFacadeLocal.find(citas.getServiciosIdservicio().getIdservicio()));
            citas.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(citas.getTipopagoidTipoPago().getIdTipoPago()));
            citasFacadeLocal.edit(citas);
        } catch (Exception e) {
            System.out.println("Error al actualizar cita: " + e);
        }
    }

    public CitaClienteView() {
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

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public ArrayList<TipoPago> getListapago() {
        return listapago;
    }

    public void setListapago(ArrayList<TipoPago> listapago) {
        this.listapago = listapago;
    }

    public ArrayList<Usuarios> getListausu() {
        return listausu;
    }

    public void setListausu(ArrayList<Usuarios> listausu) {
        this.listausu = listausu;
    }

    public ArrayList<Servicios> getListaservices() {
        return listaservices;
    }

    public void setListaservices(ArrayList<Servicios> listaservices) {
        this.listaservices = listaservices;
    }

}
