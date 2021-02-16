/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author soporte
 */
@Entity
@Table(name = "ingreso_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoProducto.findAll", query = "SELECT i FROM IngresoProducto i")
    , @NamedQuery(name = "IngresoProducto.findByIdIngreso", query = "SELECT i FROM IngresoProducto i WHERE i.idIngreso = :idIngreso")
    , @NamedQuery(name = "IngresoProducto.findByFecha", query = "SELECT i FROM IngresoProducto i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "IngresoProducto.findByCantidad", query = "SELECT i FROM IngresoProducto i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "IngresoProducto.findByDescripcion", query = "SELECT i FROM IngresoProducto i WHERE i.descripcion = :descripcion")})
public class IngresoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ingreso")
    private Integer idIngreso;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "crear_producto_idCrear_Producto", referencedColumnName = "idCrear_Producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CrearProducto crearproductoidCrearProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingresoProductoIdIngreso", fetch = FetchType.LAZY)
    private Collection<Inventario> inventarioCollection;

    public IngresoProducto() {
    }

    public IngresoProducto(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CrearProducto getCrearproductoidCrearProducto() {
        return crearproductoidCrearProducto;
    }

    public void setCrearproductoidCrearProducto(CrearProducto crearproductoidCrearProducto) {
        this.crearproductoidCrearProducto = crearproductoidCrearProducto;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngreso != null ? idIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoProducto)) {
            return false;
        }
        IngresoProducto other = (IngresoProducto) object;
        if ((this.idIngreso == null && other.idIngreso != null) || (this.idIngreso != null && !this.idIngreso.equals(other.idIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.IngresoProducto[ idIngreso=" + idIngreso + " ]";
    }
    
}
