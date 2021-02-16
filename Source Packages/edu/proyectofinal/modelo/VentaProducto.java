/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author soporte
 */
@Entity
@Table(name = "venta_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaProducto.findAll", query = "SELECT v FROM VentaProducto v")
    , @NamedQuery(name = "VentaProducto.findByIdventaProducto", query = "SELECT v FROM VentaProducto v WHERE v.idventaProducto = :idventaProducto")
    , @NamedQuery(name = "VentaProducto.findByFechaVenta", query = "SELECT v FROM VentaProducto v WHERE v.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "VentaProducto.findByCantidad", query = "SELECT v FROM VentaProducto v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "VentaProducto.findByTotal", query = "SELECT v FROM VentaProducto v WHERE v.total = :total")
    , @NamedQuery(name = "VentaProducto.findByIva", query = "SELECT v FROM VentaProducto v WHERE v.iva = :iva")})
public class VentaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idventa_producto")
    private Integer idventaProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Venta")
    private String fechaVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iva")
    private double iva;
    @JoinColumn(name = "crear_producto_idCrear_Producto", referencedColumnName = "idCrear_Producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CrearProducto crearproductoidCrearProducto;
    @JoinColumn(name = "tipo_pago_idTipo_Pago", referencedColumnName = "idTipo_Pago")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoPago tipopagoidTipoPago;
    @JoinColumn(name = "idUsuarios", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios idUsuarios;

    public VentaProducto() {
    }

    public VentaProducto(Integer idventaProducto) {
        this.idventaProducto = idventaProducto;
    }

    public VentaProducto(Integer idventaProducto, String fechaVenta, int cantidad, double total, double iva) {
        this.idventaProducto = idventaProducto;
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.total = total;
        this.iva = iva;
    }

    public Integer getIdventaProducto() {
        return idventaProducto;
    }

    public void setIdventaProducto(Integer idventaProducto) {
        this.idventaProducto = idventaProducto;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public CrearProducto getCrearproductoidCrearProducto() {
        return crearproductoidCrearProducto;
    }

    public void setCrearproductoidCrearProducto(CrearProducto crearproductoidCrearProducto) {
        this.crearproductoidCrearProducto = crearproductoidCrearProducto;
    }

    public TipoPago getTipopagoidTipoPago() {
        return tipopagoidTipoPago;
    }

    public void setTipopagoidTipoPago(TipoPago tipopagoidTipoPago) {
        this.tipopagoidTipoPago = tipopagoidTipoPago;
    }

    public Usuarios getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Usuarios idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventaProducto != null ? idventaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaProducto)) {
            return false;
        }
        VentaProducto other = (VentaProducto) object;
        if ((this.idventaProducto == null && other.idventaProducto != null) || (this.idventaProducto != null && !this.idventaProducto.equals(other.idventaProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.VentaProducto[ idventaProducto=" + idventaProducto + " ]";
    }
    
}
