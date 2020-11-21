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
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservicio")
    private Integer idservicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre_Servicio")
    private String nombreServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor_Servicio")
    private double valorServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo_Servicio")
    private int codigoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Creacion")
    private String fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripci\u00f3n")
    private String descripción;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosIdservicio", fetch = FetchType.LAZY)
    private Collection<Citas> citasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosIdservicio", fetch = FetchType.LAZY)
    private Collection<PagoServicio> pagoServicioCollection;
    @JoinColumn(name = "categoria_servicio_idCategoria_Servicio", referencedColumnName = "idCategoria_Servicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaServicio categoriaservicioidCategoriaServicio;

    public Servicios() {
    }

    public Servicios(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public Servicios(Integer idservicio, String nombreServicio, double valorServicio, int codigoServicio, String fechaCreacion, String descripción) {
        this.idservicio = idservicio;
        this.nombreServicio = nombreServicio;
        this.valorServicio = valorServicio;
        this.codigoServicio = codigoServicio;
        this.fechaCreacion = fechaCreacion;
        this.descripción = descripción;
    }

    public Integer getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @XmlTransient
    public Collection<PagoServicio> getPagoServicioCollection() {
        return pagoServicioCollection;
    }

    public void setPagoServicioCollection(Collection<PagoServicio> pagoServicioCollection) {
        this.pagoServicioCollection = pagoServicioCollection;
    }

    public CategoriaServicio getCategoriaservicioidCategoriaServicio() {
        return categoriaservicioidCategoriaServicio;
    }

    public void setCategoriaservicioidCategoriaServicio(CategoriaServicio categoriaservicioidCategoriaServicio) {
        this.categoriaservicioidCategoriaServicio = categoriaservicioidCategoriaServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicio != null ? idservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idservicio == null && other.idservicio != null) || (this.idservicio != null && !this.idservicio.equals(other.idservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.Servicios[ idservicio=" + idservicio + " ]";
    }
    
}
