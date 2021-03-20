package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T23:42:04")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, Usuarios> usuariosidUsuarios1;
    public static volatile SingularAttribute<Proveedor, Integer> crearproductoidCrearProducto;
    public static volatile CollectionAttribute<Proveedor, CrearProducto> crearProductoCollection;
    public static volatile SingularAttribute<Proveedor, String> nombre;
    public static volatile SingularAttribute<Proveedor, String> numTel;
    public static volatile SingularAttribute<Proveedor, String> diaPago;
    public static volatile SingularAttribute<Proveedor, Integer> usuariosidUsuarios;
    public static volatile SingularAttribute<Proveedor, String> direcccion;
    public static volatile SingularAttribute<Proveedor, String> ciudad;
    public static volatile SingularAttribute<Proveedor, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedor, String> nit;
    public static volatile SingularAttribute<Proveedor, String> fechar;
    public static volatile SingularAttribute<Proveedor, String> numCel;
    public static volatile SingularAttribute<Proveedor, String> razon;
    public static volatile SingularAttribute<Proveedor, String> email;

}