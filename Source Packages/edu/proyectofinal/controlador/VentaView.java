/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;


import edu.proyectofinal.facade.CrearProductoFacadeLocal;
import edu.proyectofinal.facade.TipoPagoFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.facade.VentaProductoFacadeLocal;
import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.TipoPago;
import edu.proyectofinal.modelo.Usuarios;
import edu.proyectofinal.modelo.VentaProducto;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author soporte
 */
@Named(value = "ventaView")
@ViewScoped
public class VentaView implements Serializable {
    
    private Integer idventa;
    private String mensaje=""; 
    private boolean verdad;

    public VentaView() {

    }

    @EJB
    TipoPagoFacadeLocal tipoPagoFacadeLocal;
    private ArrayList<TipoPago> listapagos = new ArrayList<>();
    private TipoPago objtpg = new TipoPago();

    @EJB
    VentaProductoFacadeLocal ventaProductoFacadeLocal;
    private ArrayList<VentaProducto> listaventa = new ArrayList<>();
    private VentaProducto objproduct = new VentaProducto();

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private ArrayList<Usuarios> listausuario = new ArrayList<>();
    private Usuarios objusr = new Usuarios();

    @EJB
    CrearProductoFacadeLocal cpf;
    private ArrayList<CrearProducto> listaprducts = new ArrayList<>();
    private CrearProducto objprtc = new CrearProducto();
    
  
    /**
     * Creates a new instance of VentaView
     */
    @PostConstruct
    public void listaproductos() {
        
        
        
        listapagos.addAll(tipoPagoFacadeLocal.findAll());

        listaventa.addAll(ventaProductoFacadeLocal.findAll());
        
        listausuario.addAll(usuariosFacadeLocal.findAll());

        listaprducts.addAll(cpf.findAll());
        
        objproduct.setCrearproductoidCrearProducto(new CrearProducto());
        
        objproduct.setTipopagoidTipoPago(new TipoPago());
        
        objproduct.setIdUsuarios(new Usuarios());

        
    }

    public void crearVenta() {  
        
        String mensaje="";
        
        try {
            
            objproduct.setCrearproductoidCrearProducto(cpf.find(objproduct.getCrearproductoidCrearProducto().getIdCrearProducto()));
            
            objproduct.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(objproduct.getTipopagoidTipoPago().getIdTipoPago()));
            
            objproduct.setIdUsuarios(usuariosFacadeLocal.find(objproduct.getIdUsuarios().getIdUsuarios()));
            
            ventaProductoFacadeLocal.create(objproduct);
            
            listaventa.add(objproduct);
            
            mensaje="Swal.fire('¡Venta creada con exito!','','success')";
            
        } catch (Exception e) {
            //System.out.println("Error al regustrar" + getClientedocu() + e);
            System.out.println("Erro"+ e );
            
             mensaje="Swal.fire('¡No se pudo crear la venta!','','error')";
            
        }
        PrimeFaces.current().executeScript(mensaje);
 
        
    }
    
    public void retornarDatos(VentaProducto objkl){
        this.objproduct = objkl;       
    }
     
    public void actualizarDatos(){
        try {
            objproduct.setCrearproductoidCrearProducto(cpf.find(objproduct.getCrearproductoidCrearProducto().getIdCrearProducto()));
            
            objproduct.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(objproduct.getTipopagoidTipoPago().getIdTipoPago()));
            
            objproduct.setIdUsuarios(usuariosFacadeLocal.find(objproduct.getIdUsuarios().getIdUsuarios()));
            
            ventaProductoFacadeLocal.edit(objproduct);
            listaventa.clear();
            listaventa.addAll(ventaProductoFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
        }
    }
    
    public void eliminar (){
              
        try {         
           ventaProductoFacadeLocal.eliminarPorId(idventa);
           listaventa.clear();
           listaventa.addAll(ventaProductoFacadeLocal.findAll()); 
           this.verdad=true;
           this.mensaje="swal('Usuario modificado' , ' con exito ', 'success')";
           System.out.println(this.mensaje + this.verdad);
           
                  
        } catch (Exception e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
            mensaje="Swal.fire('¡No se pudo eliminar!','','error')";
        }
         
        PrimeFaces.current().executeScript(this.mensaje);
        
    }

    public TipoPago getObjtpg() {
        return objtpg;
    }

    public void setObjtpg(TipoPago objtpg) {
        this.objtpg = objtpg;
    }

    public VentaProducto getObjproduct() {
        return objproduct;
    }

    public void setObjproduct(VentaProducto objproduct) {
        this.objproduct = objproduct;
    }

    public Usuarios getObjusr() {
        return objusr;
    }

    public void setObjusr(Usuarios objusr) {
        this.objusr = objusr;
    }

    public ArrayList<TipoPago> getListapagos() {
        return listapagos;
    }

    public void setListapagos(ArrayList<TipoPago> listapagos) {
        this.listapagos = listapagos;
    }

    public ArrayList<Usuarios> getListausuario() {
        return listausuario;
    }

    public void setListausuario(ArrayList<Usuarios> listausuario) {
        this.listausuario = listausuario;
    }

    public ArrayList<CrearProducto> getListaprducts() {
        return listaprducts;
    }

    public void setListaprducts(ArrayList<CrearProducto> listaprducts) {
        this.listaprducts = listaprducts;
    }

    public CrearProducto getObjprtc() {
        return objprtc;
    }

    public void setObjprtc(CrearProducto objprtc) {
        this.objprtc = objprtc;
    }

    public ArrayList<VentaProducto> getListaventa() {
        return listaventa;
    }

    public void setListaventa(ArrayList<VentaProducto> listaventa) {
        this.listaventa = listaventa;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVerdad() {
        return verdad;
    }

    public void setVerdad(boolean verdad) {
        this.verdad = verdad;
    }


}
