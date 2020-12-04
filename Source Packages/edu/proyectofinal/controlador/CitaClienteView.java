/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CitasFacadeLocal;
import edu.proyectofinal.modelo.Citas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author soporte
 */
@Named(value = "citaClienteView")
@ViewScoped
public class CitaClienteView implements Serializable{
    
    
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    private Citas citas = new Citas();
    private ArrayList<Citas> listacitas = new ArrayList<>();
    
    @Inject
    UsuarioSession usuarioSession;
    
    @PostConstruct
    public void cargaCategorias() {
        try {
            
            listacitas.addAll(citasFacadeLocal.listaCitasPer(usuarioSession.getCedula()));
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
     
//     public List<Citas> listarPorCategoria() {
//        return citasFacadeLocal.listaCitasPer(usuarioSession.getCedula());
//    }
//    
    

    
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
    
}
