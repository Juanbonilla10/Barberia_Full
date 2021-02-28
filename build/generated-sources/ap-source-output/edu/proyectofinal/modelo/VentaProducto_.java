package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.TipoPago;
import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(VentaProducto.class)
public class VentaProducto_ { 

    public static volatile SingularAttribute<VentaProducto, Double> total;
    public static volatile SingularAttribute<VentaProducto, TipoPago> tipopagoidTipoPago;
    public static volatile SingularAttribute<VentaProducto, Double> iva;
    public static volatile SingularAttribute<VentaProducto, CrearProducto> crearproductoidCrearProducto;
    public static volatile SingularAttribute<VentaProducto, Integer> cantidad;
    public static volatile SingularAttribute<VentaProducto, Integer> idventaProducto;
    public static volatile SingularAttribute<VentaProducto, Usuarios> idUsuarios;
    public static volatile SingularAttribute<VentaProducto, String> fechaVenta;

}