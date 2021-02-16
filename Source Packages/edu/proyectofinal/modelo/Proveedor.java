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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author soporte
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedor.findByNombre", query = "SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Proveedor.findByRazon", query = "SELECT p FROM Proveedor p WHERE p.razon = :razon")
    , @NamedQuery(name = "Proveedor.findByFechar", query = "SELECT p FROM Proveedor p WHERE p.fechar = :fechar")
    , @NamedQuery(name = "Proveedor.findByNumCel", query = "SELECT p FROM Proveedor p WHERE p.numCel = :numCel")
    , @NamedQuery(name = "Proveedor.findByNumTel", query = "SELECT p FROM Proveedor p WHERE p.numTel = :numTel")
    , @NamedQuery(name = "Proveedor.findByDiaPago", query = "SELECT p FROM Proveedor p WHERE p.diaPago = :diaPago")
    , @NamedQuery(name = "Proveedor.findByCiudad", query = "SELECT p FROM Proveedor p WHERE p.ciudad = :ciudad")
    , @NamedQuery(name = "Proveedor.findByDirecccion", query = "SELECT p FROM Proveedor p WHERE p.direcccion = :direcccion")
    , @NamedQuery(name = "Proveedor.findByEmail", query = "SELECT p FROM Proveedor p WHERE p.email = :email")
    , @NamedQuery(name = "Proveedor.findByUsuariosidUsuarios", query = "SELECT p FROM Proveedor p WHERE p.usuariosidUsuarios = :usuariosidUsuarios")
    , @NamedQuery(name = "Proveedor.findByCrearproductoidCrearProducto", query = "SELECT p FROM Proveedor p WHERE p.crearproductoidCrearProducto = :crearproductoidCrearProducto")
    , @NamedQuery(name = "Proveedor.findByNit", query = "SELECT p FROM Proveedor p WHERE p.nit = :nit")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "razon")
    private String razon;
    @Size(max = 45)
    @Column(name = "fechar")
    private String fechar;
    @Column(name = "num_cel")
    private Integer numCel;
    @Column(name = "num_tel")
    private Integer numTel;
    @Size(max = 45)
    @Column(name = "dia_pago")
    private String diaPago;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 45)
    @Column(name = "direcccion")
    private String direcccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuarios_idUsuarios")
    private int usuariosidUsuarios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crear_producto_idCrear_Producto")
    private int crearproductoidCrearProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nit")
    private String nit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorIdProveedor", fetch = FetchType.LAZY)
    private Collection<CrearProducto> crearProductoCollection;
    @JoinColumn(name = "usuarios_idUsuarios1", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosidUsuarios1;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, int usuariosidUsuarios, int crearproductoidCrearProducto, String nit) {
        this.idProveedor = idProveedor;
        this.usuariosidUsuarios = usuariosidUsuarios;
        this.crearproductoidCrearProducto = crearproductoidCrearProducto;
        this.nit = nit;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getFechar() {
        return fechar;
    }

    public void setFechar(String fechar) {
        this.fechar = fechar;
    }

    public Integer getNumCel() {
        return numCel;
    }

    public void setNumCel(Integer numCel) {
        this.numCel = numCel;
    }

    public Integer getNumTel() {
        return numTel;
    }

    public void setNumTel(Integer numTel) {
        this.numTel = numTel;
    }

    public String getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(String diaPago) {
        this.diaPago = diaPago;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDirecccion() {
        return direcccion;
    }

    public void setDirecccion(String direcccion) {
        this.direcccion = direcccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUsuariosidUsuarios() {
        return usuariosidUsuarios;
    }

    public void setUsuariosidUsuarios(int usuariosidUsuarios) {
        this.usuariosidUsuarios = usuariosidUsuarios;
    }

    public int getCrearproductoidCrearProducto() {
        return crearproductoidCrearProducto;
    }

    public void setCrearproductoidCrearProducto(int crearproductoidCrearProducto) {
        this.crearproductoidCrearProducto = crearproductoidCrearProducto;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @XmlTransient
    public Collection<CrearProducto> getCrearProductoCollection() {
        return crearProductoCollection;
    }

    public void setCrearProductoCollection(Collection<CrearProducto> crearProductoCollection) {
        this.crearProductoCollection = crearProductoCollection;
    }

    public Usuarios getUsuariosidUsuarios1() {
        return usuariosidUsuarios1;
    }

    public void setUsuariosidUsuarios1(Usuarios usuariosidUsuarios1) {
        this.usuariosidUsuarios1 = usuariosidUsuarios1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
