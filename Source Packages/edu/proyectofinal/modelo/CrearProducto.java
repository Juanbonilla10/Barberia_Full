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
@Table(name = "crear_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrearProducto.findAll", query = "SELECT c FROM CrearProducto c")
    , @NamedQuery(name = "CrearProducto.findByIdCrearProducto", query = "SELECT c FROM CrearProducto c WHERE c.idCrearProducto = :idCrearProducto")
    , @NamedQuery(name = "CrearProducto.findByDescripcion", query = "SELECT c FROM CrearProducto c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CrearProducto.findByReferencia", query = "SELECT c FROM CrearProducto c WHERE c.referencia = :referencia")
    , @NamedQuery(name = "CrearProducto.findByCodigoBarras", query = "SELECT c FROM CrearProducto c WHERE c.codigoBarras = :codigoBarras")
    , @NamedQuery(name = "CrearProducto.findByCantidad", query = "SELECT c FROM CrearProducto c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "CrearProducto.findByPrecioProveedor", query = "SELECT c FROM CrearProducto c WHERE c.precioProveedor = :precioProveedor")
    , @NamedQuery(name = "CrearProducto.findByPrecioPublico", query = "SELECT c FROM CrearProducto c WHERE c.precioPublico = :precioPublico")
    , @NamedQuery(name = "CrearProducto.findByFechaRegistro", query = "SELECT c FROM CrearProducto c WHERE c.fechaRegistro = :fechaRegistro")})
public class CrearProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCrear_Producto")
    private Integer idCrearProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Codigo_Barras")
    private String codigoBarras;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Precio_Proveedor")
    private String precioProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Precio_Publico")
    private String precioPublico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Registro")
    private String fechaRegistro;
    @JoinColumn(name = "proveedor_id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor proveedorIdProveedor;

    public CrearProducto() {
    }

    public CrearProducto(Integer idCrearProducto) {
        this.idCrearProducto = idCrearProducto;
    }

    public CrearProducto(Integer idCrearProducto, String descripcion, String referencia, String codigoBarras, String precioProveedor, String precioPublico, String fechaRegistro) {
        this.idCrearProducto = idCrearProducto;
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.codigoBarras = codigoBarras;
        this.precioProveedor = precioProveedor;
        this.precioPublico = precioPublico;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCrearProducto() {
        return idCrearProducto;
    }

    public void setIdCrearProducto(Integer idCrearProducto) {
        this.idCrearProducto = idCrearProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(String precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public String getPrecioPublico() {
        return precioPublico;
    }

    public void setPrecioPublico(String precioPublico) {
        this.precioPublico = precioPublico;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Proveedor getProveedorIdProveedor() {
        return proveedorIdProveedor;
    }

    public void setProveedorIdProveedor(Proveedor proveedorIdProveedor) {
        this.proveedorIdProveedor = proveedorIdProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCrearProducto != null ? idCrearProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrearProducto)) {
            return false;
        }
        CrearProducto other = (CrearProducto) object;
        if ((this.idCrearProducto == null && other.idCrearProducto != null) || (this.idCrearProducto != null && !this.idCrearProducto.equals(other.idCrearProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.CrearProducto[ idCrearProducto=" + idCrearProducto + " ]";
    }
    
}
