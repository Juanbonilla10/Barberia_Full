/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;


import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.Usuarios;
import edu.proyectofinal.utilidades.Email;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Laura Ballen
 */
@Named(value = "listaUsuarioView")
@ViewScoped
public class ListaUsuarioView implements Serializable {

    /**
     * Creates a new instance of ListaUsuarioView
     */
    public ListaUsuarioView() {
    }
    
    @EJB
    UsuariosFacadeLocal usuarioFacadeLocal;
    
    private ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    
    private Usuarios usReg = new Usuarios();
    
    
    @Inject
    UsuarioSession usuarioSession;

    /**
     * Creates a new instance of AdministradorView
     */
    @PostConstruct
    public void leerListaUsuarios() {
        try {
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        
    }
    
    public void cargaUsuarioEditar(Usuarios usuEditar) {
        this.usReg = usuEditar;
    }
    
    public void removerUsuario(Usuarios usuRem) {
        String mensajeSw = " ";
        try {
            usuarioFacadeLocal.remove(usuRem);
            listaUsuarios.remove(usuRem);
            mensajeSw = "swal('Usuario removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas removiendo' , ' al usuario  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void registrarUser() {
        String mensajeSw = "";
        try {
            usReg.setFechaNacimiento("2020");
            usuarioFacadeLocal.create(usReg);
            listaUsuarios.add(usReg);
            mensajeSw = "swal('Usuario registrado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , ' Ya se encuentra registrado  ', 'error')";
            System.out.println("Error" + e);
        }
        usReg = new Usuarios();
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    
    public void editarUsuario() {
        String mensajeSw = "";
        try {
            usuarioFacadeLocal.edit(usReg);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            mensajeSw = "swal('Usuario modificado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas modificando' , ' al usuario  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void correoMasivo(){
        try {
            for (Usuarios lUsuario : listaUsuarios) {
                Email.sendBienvenido(lUsuario.getEmail(), 
                        lUsuario.getNombres() + " " +lUsuario.getApellidos(),
                        lUsuario.getEmail(), lUsuario.getContrasena());
            }
        } catch (Exception e) {
        }
        
    }
    
    public void reporteUsuarios(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("UsuariosReporte", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/Usuarios.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista De Usuarios.pdf");
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
    
    
    public ArrayList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuarios getUsReg() {
        return usReg;
    }

    public void setUsReg(Usuarios usReg) {
        this.usReg = usReg;
    }
    
    
}
