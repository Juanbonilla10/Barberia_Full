package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Cronograma;
import edu.proyectofinal.modelo.Servicios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(CategoriaServicio.class)
public class CategoriaServicio_ { 

    public static volatile SingularAttribute<CategoriaServicio, String> descripcion;
    public static volatile SingularAttribute<CategoriaServicio, String> fecha;
    public static volatile CollectionAttribute<CategoriaServicio, Cronograma> cronogramaCollection;
    public static volatile SingularAttribute<CategoriaServicio, String> tipoServicio;
    public static volatile SingularAttribute<CategoriaServicio, Integer> idCategoriaServicio;
    public static volatile CollectionAttribute<CategoriaServicio, Servicios> serviciosCollection;

}