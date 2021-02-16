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
@Table(name = "categoria_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaServicio.findAll", query = "SELECT c FROM CategoriaServicio c")
    , @NamedQuery(name = "CategoriaServicio.findByIdCategoriaServicio", query = "SELECT c FROM CategoriaServicio c WHERE c.idCategoriaServicio = :idCategoriaServicio")
    , @NamedQuery(name = "CategoriaServicio.findByTipoServicio", query = "SELECT c FROM CategoriaServicio c WHERE c.tipoServicio = :tipoServicio")
    , @NamedQuery(name = "CategoriaServicio.findByDescripcion", query = "SELECT c FROM CategoriaServicio c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CategoriaServicio.findByFecha", query = "SELECT c FROM CategoriaServicio c WHERE c.fecha = :fecha")})
public class CategoriaServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoria_Servicio")
    private Integer idCategoriaServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo_Servicio")
    private String tipoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha")
    private String fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaservicioidCategoriaServicio", fetch = FetchType.LAZY)
    private Collection<Servicios> serviciosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaservicioidCategoriaServicio", fetch = FetchType.LAZY)
    private Collection<Cronograma> cronogramaCollection;

    public CategoriaServicio() {
    }

    public CategoriaServicio(Integer idCategoriaServicio) {
        this.idCategoriaServicio = idCategoriaServicio;
    }

    public CategoriaServicio(Integer idCategoriaServicio, String tipoServicio, String descripcion, String fecha) {
        this.idCategoriaServicio = idCategoriaServicio;
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getIdCategoriaServicio() {
        return idCategoriaServicio;
    }

    public void setIdCategoriaServicio(Integer idCategoriaServicio) {
        this.idCategoriaServicio = idCategoriaServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @XmlTransient
    public Collection<Cronograma> getCronogramaCollection() {
        return cronogramaCollection;
    }

    public void setCronogramaCollection(Collection<Cronograma> cronogramaCollection) {
        this.cronogramaCollection = cronogramaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaServicio != null ? idCategoriaServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaServicio)) {
            return false;
        }
        CategoriaServicio other = (CategoriaServicio) object;
        if ((this.idCategoriaServicio == null && other.idCategoriaServicio != null) || (this.idCategoriaServicio != null && !this.idCategoriaServicio.equals(other.idCategoriaServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.CategoriaServicio[ idCategoriaServicio=" + idCategoriaServicio + " ]";
    }
    
}
