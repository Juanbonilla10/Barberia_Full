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
@Table(name = "cronograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronograma.findAll", query = "SELECT c FROM Cronograma c")})
public class Cronograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCronograma")
    private Integer idCronograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Inicio")
    private String fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hora_Inicio")
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hora_Fin")
    private String horaFin;
    @JoinColumn(name = "categoria_servicio_idCategoria_Servicio", referencedColumnName = "idCategoria_Servicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaServicio categoriaservicioidCategoriaServicio;
    @JoinColumn(name = "usuarios_idUsuarios", referencedColumnName = "Num_Identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosidUsuarios;

    public Cronograma() {
    }

    public Cronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public Cronograma(Integer idCronograma, String fechaInicio, String horaInicio, String horaFin) {
        this.idCronograma = idCronograma;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public CategoriaServicio getCategoriaservicioidCategoriaServicio() {
        return categoriaservicioidCategoriaServicio;
    }

    public void setCategoriaservicioidCategoriaServicio(CategoriaServicio categoriaservicioidCategoriaServicio) {
        this.categoriaservicioidCategoriaServicio = categoriaservicioidCategoriaServicio;
    }

    public Usuarios getUsuariosidUsuarios() {
        return usuariosidUsuarios;
    }

    public void setUsuariosidUsuarios(Usuarios usuariosidUsuarios) {
        this.usuariosidUsuarios = usuariosidUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronograma != null ? idCronograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cronograma)) {
            return false;
        }
        Cronograma other = (Cronograma) object;
        if ((this.idCronograma == null && other.idCronograma != null) || (this.idCronograma != null && !this.idCronograma.equals(other.idCronograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.Cronograma[ idCronograma=" + idCronograma + " ]";
    }
    
}
