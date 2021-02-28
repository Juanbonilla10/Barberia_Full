package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.IngresoProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> precio;
    public static volatile SingularAttribute<Inventario, IngresoProducto> ingresoProductoIdIngreso;
    public static volatile SingularAttribute<Inventario, CrearProducto> crearproductoidCrearProducto;
    public static volatile SingularAttribute<Inventario, Integer> cantidad;
    public static volatile SingularAttribute<Inventario, Integer> idInventario;

}