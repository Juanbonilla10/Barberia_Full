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
@Table(name = "pago_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoServicio.findAll", query = "SELECT p FROM PagoServicio p")
    , @NamedQuery(name = "PagoServicio.findByIdPagoServicio", query = "SELECT p FROM PagoServicio p WHERE p.idPagoServicio = :idPagoServicio")
    , @NamedQuery(name = "PagoServicio.findByFechaAtencion", query = "SELECT p FROM PagoServicio p WHERE p.fechaAtencion = :fechaAtencion")
    , @NamedQuery(name = "PagoServicio.findByValorServicio", query = "SELECT p FROM PagoServicio p WHERE p.valorServicio = :valorServicio")
    , @NamedQuery(name = "PagoServicio.findByFechaPagoBarbero", query = "SELECT p FROM PagoServicio p WHERE p.fechaPagoBarbero = :fechaPagoBarbero")
    , @NamedQuery(name = "PagoServicio.findByAbono", query = "SELECT p FROM PagoServicio p WHERE p.abono = :abono")
    , @NamedQuery(name = "PagoServicio.findBySaldoPendiente", query = "SELECT p FROM PagoServicio p WHERE p.saldoPendiente = :saldoPendiente")})
public class PagoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPago_Servicio")
    private Integer idPagoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Atencion")
    private String fechaAtencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor_Servicio")
    private double valorServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha_Pago_Barbero")
    private String fechaPagoBarbero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Abono")
    private double abono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Saldo_Pendiente")
    private double saldoPendiente;
    @JoinColumn(name = "servicios_idservicio", referencedColumnName = "idservicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servicios serviciosIdservicio;
    @JoinColumn(name = "usuarios_idUsuarios", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosidUsuarios;

    public PagoServicio() {
    }

    public PagoServicio(Integer idPagoServicio) {
        this.idPagoServicio = idPagoServicio;
    }

    public PagoServicio(Integer idPagoServicio, String fechaAtencion, double valorServicio, String fechaPagoBarbero, double abono, double saldoPendiente) {
        this.idPagoServicio = idPagoServicio;
        this.fechaAtencion = fechaAtencion;
        this.valorServicio = valorServicio;
        this.fechaPagoBarbero = fechaPagoBarbero;
        this.abono = abono;
        this.saldoPendiente = saldoPendiente;
    }

    public Integer getIdPagoServicio() {
        return idPagoServicio;
    }

    public void setIdPagoServicio(Integer idPagoServicio) {
        this.idPagoServicio = idPagoServicio;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public double getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    public String getFechaPagoBarbero() {
        return fechaPagoBarbero;
    }

    public void setFechaPagoBarbero(String fechaPagoBarbero) {
        this.fechaPagoBarbero = fechaPagoBarbero;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public Servicios getServiciosIdservicio() {
        return serviciosIdservicio;
    }

    public void setServiciosIdservicio(Servicios serviciosIdservicio) {
        this.serviciosIdservicio = serviciosIdservicio;
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
        hash += (idPagoServicio != null ? idPagoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoServicio)) {
            return false;
        }
        PagoServicio other = (PagoServicio) object;
        if ((this.idPagoServicio == null && other.idPagoServicio != null) || (this.idPagoServicio != null && !this.idPagoServicio.equals(other.idPagoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.PagoServicio[ idPagoServicio=" + idPagoServicio + " ]";
    }
    
}
