package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.Inventario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T23:42:04")
@StaticMetamodel(IngresoProducto.class)
public class IngresoProducto_ { 

    public static volatile SingularAttribute<IngresoProducto, String> descripcion;
    public static volatile SingularAttribute<IngresoProducto, String> fecha;
    public static volatile SingularAttribute<IngresoProducto, Integer> idIngreso;
    public static volatile SingularAttribute<IngresoProducto, CrearProducto> crearproductoidCrearProducto;
    public static volatile SingularAttribute<IngresoProducto, Integer> cantidad;
    public static volatile CollectionAttribute<IngresoProducto, Inventario> inventarioCollection;

}