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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author soporte
 */
@Named(value = "citaView")
@ViewScoped
public class CitaView implements Serializable {

    private Integer idcita;
    private String fecha_primeprivate ;
    private String ultima_fecha;

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

    @Inject
    UsuarioSession usuarioSession;
    
    
    

    /**
     * Creates a new instance of CitaView
     */
    public CitaView() {
    }

    @PostConstruct
    public void cargadatos() {

        listauser.addAll(usuariosFacadeLocal.findAll());
        listaservicios.addAll(serviciosFacadeLocal.findAll());
        listapagos.addAll(tipoPagoFacadeLocal.findAll());
        listacitas.addAll(citasFacadeLocal.findAll());

        citas.setServiciosIdservicio(new Servicios());
        citas.setUsuariosidUsuarios(new Usuarios());
        citas.setTipopagoidTipoPago(new TipoPago());

    }

    public void crearCita() {
        try {

            citas.setServiciosIdservicio(serviciosFacadeLocal.find(citas.getServiciosIdservicio().getIdservicio()));
            citas.setUsuariosidUsuarios(usuariosFacadeLocal.find(citas.getUsuariosidUsuarios().getIdUsuarios()));
            citas.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(citas.getTipopagoidTipoPago().getIdTipoPago()));
            citasFacadeLocal.create(citas);
            listacitas.clear();
            listacitas.addAll(citasFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cargaDatos(Citas objcitasp) {
        this.citas = objcitasp;
    }

    public void actualizarDatos() {

        try {

            citas.setServiciosIdservicio(serviciosFacadeLocal.find(citas.getServiciosIdservicio().getIdservicio()));
            citas.setUsuariosidUsuarios(usuariosFacadeLocal.find(citas.getUsuariosidUsuarios().getIdUsuarios()));
            citas.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(citas.getTipopagoidTipoPago().getIdTipoPago()));

            citasFacadeLocal.edit(citas);
            listacitas.clear();
            listacitas.addAll(citasFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public void eliminarDatos() {
        try {
            citasFacadeLocal.eliminarDatos(idcita);
            listacitas.clear();
            listacitas.addAll(citasFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error al eliminar desde el controlador: " + e.getMessage());
        }
    }

    public void reporteCitas() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("Elaborado", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("Img", context.getRealPath("/images/AYT.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/Citas.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Citas.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public void reporteVentasPorMes() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("fecha",getFecha_primeprivate());
            parametro.put("fehca_ultima", getUltima_fecha());
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());
              
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/productospormes.jasper"));
            
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista Mas Vendidos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + q.getMessage());
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

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public String getUltima_fecha() {
        return ultima_fecha;
    }

    public void setUltima_fecha(String ultima_fecha) {
        this.ultima_fecha = ultima_fecha;
    }

    public String getFecha_primeprivate() {
        return fecha_primeprivate;
    }

    public void setFecha_primeprivate(String fecha_primeprivate) {
        this.fecha_primeprivate = fecha_primeprivate;
    }




}
