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
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByIdCitas", query = "SELECT c FROM Citas c WHERE c.idCitas = :idCitas")
    , @NamedQuery(name = "Citas.findByTelefono", query = "SELECT c FROM Citas c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Citas.findByFecha", query = "SELECT c FROM Citas c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Citas.findByHora", query = "SELECT c FROM Citas c WHERE c.hora = :hora")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCitas")
    private Integer idCitas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono")
    private double telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hora")
    private String hora;
    @JoinColumn(name = "servicios_idservicio", referencedColumnName = "idservicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servicios serviciosIdservicio;
    @JoinColumn(name = "tipo_pago_idTipo_Pago", referencedColumnName = "idTipo_Pago")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPago tipopagoidTipoPago;
    @JoinColumn(name = "usuarios_idUsuarios", referencedColumnName = "Num_Identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosidUsuarios;

    public Citas() {
    }

    public Citas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Citas(Integer idCitas, double telefono, String fecha, String hora) {
        this.idCitas = idCitas;
        this.telefono = telefono;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Servicios getServiciosIdservicio() {
        return serviciosIdservicio;
    }

    public void setServiciosIdservicio(Servicios serviciosIdservicio) {
        this.serviciosIdservicio = serviciosIdservicio;
    }

    public TipoPago getTipopagoidTipoPago() {
        return tipopagoidTipoPago;
    }

    public void setTipopagoidTipoPago(TipoPago tipopagoidTipoPago) {
        this.tipopagoidTipoPago = tipopagoidTipoPago;
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
        hash += (idCitas != null ? idCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.idCitas == null && other.idCitas != null) || (this.idCitas != null && !this.idCitas.equals(other.idCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.proyectofinal.modelo.Citas[ idCitas=" + idCitas + " ]";
    }
    
}
