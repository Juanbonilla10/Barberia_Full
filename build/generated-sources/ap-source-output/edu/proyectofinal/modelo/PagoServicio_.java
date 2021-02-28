package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Servicios;
import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(PagoServicio.class)
public class PagoServicio_ { 

    public static volatile SingularAttribute<PagoServicio, Servicios> serviciosIdservicio;
    public static volatile SingularAttribute<PagoServicio, Integer> idPagoServicio;
    public static volatile SingularAttribute<PagoServicio, String> fechaPagoBarbero;
    public static volatile SingularAttribute<PagoServicio, Double> saldoPendiente;
    public static volatile SingularAttribute<PagoServicio, Double> abono;
    public static volatile SingularAttribute<PagoServicio, String> fechaAtencion;
    public static volatile SingularAttribute<PagoServicio, Double> valorServicio;
    public static volatile SingularAttribute<PagoServicio, Usuarios> usuariosidUsuarios;

}