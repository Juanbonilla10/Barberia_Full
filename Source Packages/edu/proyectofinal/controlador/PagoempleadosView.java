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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Laura Ballen
 */
@Named(value = "pagoempleadosView")
@ViewScoped
public class PagoempleadosView implements Serializable {
    
    
    private String fechaIn;
    private String fechaFn;
    

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

        listausuario.addAll(usuariosFacadeLocal.lista(3));
        listaservicios.addAll(serviciosFacadeLocal.findAll());
        listapagoservicios.addAll(pagoServicioFacadeLocal.findAll());

        objpago.setServiciosIdservicio(new Servicios());
        objpago.setUsuariosidUsuarios(new Usuarios());
    }

    public void crearPago() {
        String mensajeSw = "";
        try {

            objpago.setServiciosIdservicio(serviciosFacadeLocal.find(objpago.getServiciosIdservicio().getIdservicio()));
            objpago.setUsuariosidUsuarios(usuariosFacadeLocal.find(objpago.getUsuariosidUsuarios().getIdUsuarios()));

            pagoServicioFacadeLocal.create(objpago);
            listapagoservicios.add(objpago);
            

        mensajeSw = "swal('Pago creado' , ' con exito ', 'success')";

        } catch (Exception e) {
            //System.out.println("Error al regustrar" + getClientedocu() + e);
            System.out.println("Error" + e);
        } 
        PrimeFaces.current().executeScript(mensajeSw);
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
    
    public void reporteVentasPorEmpleado() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("fechaInicial",getFechaIn());
            parametro.put("fechaFinal", getFechaFn());
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());
              
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/ventasPersonal.jasper"));
            
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

    public String getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIn = fechaIn;
    }

    public String getFechaFn() {
        return fechaFn;
    }

    public void setFechaFn(String fechaFn) {
        this.fechaFn = fechaFn;
    }

}
