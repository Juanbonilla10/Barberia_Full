package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Citas;
import edu.proyectofinal.modelo.VentaProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T23:42:04")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ { 

    public static volatile SingularAttribute<TipoPago, String> descripcion;
    public static volatile SingularAttribute<TipoPago, Integer> tipoPagoco;
    public static volatile SingularAttribute<TipoPago, Integer> idTipoPago;
    public static volatile CollectionAttribute<TipoPago, Citas> citasCollection;
    public static volatile CollectionAttribute<TipoPago, VentaProducto> ventaProductoCollection;

}