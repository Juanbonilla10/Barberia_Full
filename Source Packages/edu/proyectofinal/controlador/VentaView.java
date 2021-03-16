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
import javax.faces.context.FacesContext;
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
    private String mensaje = "";
    private boolean verdad;
    private int consultaStock = 0;
    private int validacion = 0;
    private int insertarProducto = 0;
    private String valorS = "";
    private int CantidadInterface = 0;

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

    private ArrayList<CrearProducto> listaStockDisponible = new ArrayList<>();

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

        String mensajeSw = "";


        consultaStock = ventaProductoFacadeLocal.validaStock(getInsertarProducto());
        //System.out.println("Listado es : " + listaStockDisponible.get(listaStockDisponible.size() -1));

        //System.out.println("Numero es 1 :" + listaStockDisponible.get(0));        
        //System.out.println("Numero es 2 :" +  objprtc.getCantidad());
        //for(int x = 0; x < listaStockDisponible.size();x++){
        //  System.out.println("Imprmiendo valor:" + listaStockDisponible.get(x));
        // }
        System.out.println("erda mi pana" + consultaStock);

        if (getCantidadInterface() > getConsultaStock()) {

            mensajeSw = "swal('¡Error al crear venta!','','error')";
        } else {

            try {

                objproduct.setCrearproductoidCrearProducto(cpf.find(getInsertarProducto()));

                objproduct.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(objproduct.getTipopagoidTipoPago().getIdTipoPago()));

                objproduct.setIdUsuarios(usuariosFacadeLocal.find(objproduct.getIdUsuarios().getIdUsuarios()));

                objproduct.setCantidad(getCantidadInterface());

                ventaProductoFacadeLocal.create(objproduct);

                listaventa.add(objproduct);

                mensajeSw = "swal('¡Venta creada con exito!','','success')";

                System.out.println("Variable valor: " + validacion);

            } catch (Exception e) {
                //System.out.println("Error al regustrar" + getClientedocu() + e);
                System.out.println("Erro" + e.getMessage());

                mensajeSw = "swal(¡Error al crear venta!','','warning')";
            }
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void retornarDatos(VentaProducto objkl) {
        this.objproduct = objkl;
    }

    public void actualizarDatos() {
        
        String mensajeSW= "";
        
        try {
            objproduct.setCrearproductoidCrearProducto(cpf.find(objproduct.getCrearproductoidCrearProducto().getIdCrearProducto()));

            objproduct.setTipopagoidTipoPago(tipoPagoFacadeLocal.find(objproduct.getTipopagoidTipoPago().getIdTipoPago()));

            objproduct.setIdUsuarios(usuariosFacadeLocal.find(objproduct.getIdUsuarios().getIdUsuarios()));

            ventaProductoFacadeLocal.edit(objproduct);
            listaventa.clear();
            listaventa.addAll(ventaProductoFacadeLocal.findAll());
            
            mensajeSW= "swal('Venta actualizada','con exito','success')";
            
        } catch (Exception e) {
            System.out.println("Error al actualizar:" + e);
            mensajeSW= "swal('Error al actualizar registro',' ','warning')";
        }
        
        PrimeFaces.current().executeScript(mensajeSW);
        
    }

    public void eliminar() {

        try {
            ventaProductoFacadeLocal.eliminarPorId(idventa);
            listaventa.clear();
            listaventa.addAll(ventaProductoFacadeLocal.findAll());
            this.verdad = true;
            this.mensaje = "swal('Venta eliminada' , ' con exito ', 'success')";
            System.out.println(this.mensaje + this.verdad);

        } catch (Exception e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
            mensaje = "Swal.fire('¡No se pudo eliminar!','','error')";
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

    public int getConsultaStock() {
        return consultaStock;
    }

    public void setConsultaStock(int consultaStock) {
        this.consultaStock = consultaStock;
    }

    public int getInsertarProducto() {
        return insertarProducto;
    }

    public void setInsertarProducto(int insertarProducto) {
        this.insertarProducto = insertarProducto;
    }

    public ArrayList<CrearProducto> getListaStockDisponible() {
        return listaStockDisponible;
    }

    public void setListaStockDisponible(ArrayList<CrearProducto> listaStockDisponible) {
        this.listaStockDisponible = listaStockDisponible;
    }

    public int getValidacion() {
        return validacion;
    }

    public void setValidacion(int validacion) {
        this.validacion = validacion;
    }

    public String getValorS() {
        return valorS;
    }

    public void setValorS(String valorS) {
        this.valorS = valorS;
    }

    public int getCantidadInterface() {
        return CantidadInterface;
    }

    public void setCantidadInterface(int CantidadInterface) {
        this.CantidadInterface = CantidadInterface;
    }

}
