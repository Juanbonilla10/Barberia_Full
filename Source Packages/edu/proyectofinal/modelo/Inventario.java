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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author soporte
 */
@Entity
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")
    , @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario")
    , @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Inventario.findByPrecio", query = "SELECT i FROM Inventario i WHERE i.precio = :precio")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario")
    private Integer idInventario;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Integer precio;
    @JoinColumn(name = "crear_producto_idCrear_Producto", referencedColumnName = "idCrear_Producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CrearProducto crearproductoidCrearProducto;
    @JoinColumn(name = "ingreso_producto_id_ingreso", referencedColumnName = "id_ingreso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private IngresoProducto ingresoProductoIdIngreso;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public CrearProducto getCrearproductoidCrearProducto() {
        return crearproductoidCrearProducto;
    }

    public void setCrearproductoidCrearProducto(CrearProducto crearproductoidCrearProducto) {
        this.crearproductoidCrearProducto = crearproductoidCrearProducto;
    }

    public IngresoProducto getIngresoProductoIdIngreso() {
        return ingresoProductoIdIngreso;
    }

    public void setIngresoProductoIdIngreso(IngresoProducto ingresoProductoIdIngreso) {
        this.ingresoProductoIdIngreso = ingresoProductoIdIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}
