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
    
    @Inject
    UsuarioSession usuarioSession;

    public ProductosView() {
    }

    @PostConstruct
    public void listprod() {
        listaproveedor.addAll(proveedorFacadeLocal.findAll());
        listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());

        objcrpro.setProveedorIdProveedor(new Proveedor());

    }

    public void registraProd() {
        try {

            crearProductoFacadeLocal.create(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("No registrado busque mas opciones" + e);
        }

        objcrpro = new CrearProducto();
    }

    public void retornaProducto(CrearProducto objpros) {
        this.objcrpro = objpros;
    }

    public void actualizarProv() {
        try {
            crearProductoFacadeLocal.edit(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
    }

    public void eliminarprod(CrearProducto prorem) {
        try {
            crearProductoFacadeLocal.remove(objcrpro);
            listaCrearProducto.remove(objcrpro);
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
    }
    
    public void reporteProductos(){
        
         FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("Usuario", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("imgUsuario", context.getRealPath("/images/AYT.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/listaproductos.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista De Productos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
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
