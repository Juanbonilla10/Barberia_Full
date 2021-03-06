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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Brayan Algecira
 */
@Named(value = "proveedorView")
@ViewScoped
public class ProveedorView implements Serializable {

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
    private Proveedor objprov = new Proveedor();

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private ArrayList<Usuarios> listausuario = new ArrayList<>();
    private Usuarios objusru = new Usuarios();

    @Inject
    UsuarioSession usuarioSession;

    public ProveedorView() {
    }

    @PostConstruct
    public void listaProv() {
        listaproveedor.addAll(proveedorFacadeLocal.findAll());
        listausuario.addAll(usuariosFacadeLocal.findAll());

        objprov.setUsuariosidUsuarios1(new Usuarios());

    }

    public void registrarProv() {
        String mensajeSw = " ";
        try {

            proveedorFacadeLocal.create(objprov);
            listaproveedor.clear();
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
            mensajeSw = "Swal.fire({\n"
                    + "  title: 'Quiere registrar el proveedor?',\n"
                    + "  icon: 'warning',\n"
                    + "  showCancelButton: true,\n"
                    + "  confirmButtonColor: '#3085d6',\n"
                    + "  cancelButtonColor: '#d33',\n"
                    + "  confirmButtonText: 'Si, Registrar!'\n"
                    + "}).then((result) => {\n"
                    + "  if (result.isConfirmed) {\n"
                    + "    Swal.fire(\n"
                    + "      'Registrado!',\n"
                    + "      'Proveedor registrado con exito.',\n"
                    + "      'success'\n"
                    + "    )\n"
                    + "  }\n"
                    + "})";
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            mensajeSw = "swal('Problemas al registrar el proveedor' , ' Verifique los campos  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void retornaDatos(Proveedor objpr) {
        this.objprov = objpr;
    }

    public void actualizarProv() {
        String mensajeSw = " ";
        try {
            proveedorFacadeLocal.edit(objprov);
            listaproveedor.clear();
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
            mensajeSw = "swal('Proveedor actualizado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas al actualizar el proveedor' , ' Verifique los campos  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void eliminarProv(Proveedor prorem) {
        String mensajeSw = " ";
        try {
            proveedorFacadeLocal.remove(prorem);
            listaproveedor.remove(prorem);
            listaproveedor.clear();
            listaproveedor.addAll(proveedorFacadeLocal.findAll());
            mensajeSw = "swal('Proveedor removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas al remover el proveedor' , ' El proveedor tiene productos registrados  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void reporteProveedor() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("Usuario", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("img", context.getRealPath("/images/AYT.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/listaproveedor.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista De Proveedores.pdf");
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
