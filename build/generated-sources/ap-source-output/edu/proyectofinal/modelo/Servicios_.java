package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CategoriaServicio;
import edu.proyectofinal.modelo.Citas;
import edu.proyectofinal.modelo.PagoServicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(Servicios.class)
public class Servicios_ { 

    public static volatile SingularAttribute<Servicios, String> nombreServicio;
    public static volatile SingularAttribute<Servicios, Integer> codigoServicio;
    public static volatile CollectionAttribute<Servicios, PagoServicio> pagoServicioCollection;
    public static volatile SingularAttribute<Servicios, String> descripción;
    public static volatile SingularAttribute<Servicios, CategoriaServicio> categoriaservicioidCategoriaServicio;
    public static volatile SingularAttribute<Servicios, String> fechaCreacion;
    public static volatile CollectionAttribute<Servicios, Citas> citasCollection;
    public static volatile SingularAttribute<Servicios, Double> valorServicio;
    public static volatile SingularAttribute<Servicios, Integer> idservicio;

}