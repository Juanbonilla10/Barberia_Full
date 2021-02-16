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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "horarios_cronograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorariosCronograma.findAll", query = "SELECT h FROM HorariosCronograma h")
    , @NamedQuery(name = "HorariosCronograma.findByIdhorariosCronograma", query = "SELECT h FROM HorariosCronograma h WHERE h.idhorariosCronograma = :idhorariosCronograma")
    , @NamedQuery(name = "HorariosCronograma.findByHora", query = "SELECT h FROM HorariosCronograma h WHERE h.hora = :hora")
    , @NamedQuery(name = "HorariosCronograma.findByStatus", query = "SELECT h FROM HorariosCronograma h WHERE h.status = :status")})
public class HorariosCronograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhorarios_cronograma")
    private Integer idhorariosCronograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hora")
    private String hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;

    public HorariosCronograma() {
    }

    public HorariosCronograma(Integer idhorariosCronograma) {
        this.idhorariosCronograma = idhorariosCronograma;
    }

    public HorariosCronograma(Integer idhorariosCronograma, String hora, String status) {
        this.idhorariosCronograma = idhorariosCronograma;
        this.hora = hora;
        this.status = status;
    }

    public Integer getIdhorariosCronograma() {
        return idhorariosCronograma;
    }

    public void setIdhorariosCronograma(Integer idhorariosCronograma) {
        this.idhorariosCronograma = idhorariosCronograma;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorariosCronograma != null ? idhorariosCronograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariosCronograma)) {
            return false;
        }
        HorariosCronograma other = (HorariosCronograma) object;
        if ((this.idhorariosCronograma == null && other.idhorariosCronograma != null) || (this.idhorariosCronograma != null && !this.idhorariosCronograma.equals(other.idhorariosCronograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.HorariosCronograma[ idhorariosCronograma=" + idhorariosCronograma + " ]";
    }
    
}
