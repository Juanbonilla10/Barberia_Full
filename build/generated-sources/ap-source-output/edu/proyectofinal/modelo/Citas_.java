package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Servicios;
import edu.proyectofinal.modelo.TipoPago;
import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(Citas.class)
public class Citas_ { 

    public static volatile SingularAttribute<Citas, Integer> idCitas;
    public static volatile SingularAttribute<Citas, String> fecha;
    public static volatile SingularAttribute<Citas, Servicios> serviciosIdservicio;
    public static volatile SingularAttribute<Citas, TipoPago> tipopagoidTipoPago;
    public static volatile SingularAttribute<Citas, String> hora;
    public static volatile SingularAttribute<Citas, Double> telefono;
    public static volatile SingularAttribute<Citas, Usuarios> usuariosidUsuarios;

}