package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Citas;
import edu.proyectofinal.modelo.Cronograma;
import edu.proyectofinal.modelo.PagoServicio;
import edu.proyectofinal.modelo.Proveedor;
import edu.proyectofinal.modelo.Rol;
import edu.proyectofinal.modelo.VentaProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> apellidos;
    public static volatile CollectionAttribute<Usuarios, PagoServicio> pagoServicioCollection;
    public static volatile SingularAttribute<Usuarios, String> fechaNacimiento;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile SingularAttribute<Usuarios, String> nombres;
    public static volatile CollectionAttribute<Usuarios, VentaProducto> ventaProductoCollection;
    public static volatile CollectionAttribute<Usuarios, Cronograma> cronogramaCollection;
    public static volatile SingularAttribute<Usuarios, Integer> numIdentificacion;
    public static volatile SingularAttribute<Usuarios, Rol> rolidURol;
    public static volatile CollectionAttribute<Usuarios, Proveedor> proveedorCollection;
    public static volatile SingularAttribute<Usuarios, String> genero;
    public static volatile SingularAttribute<Usuarios, String> contrasena;
    public static volatile CollectionAttribute<Usuarios, Citas> citasCollection;
    public static volatile SingularAttribute<Usuarios, String> telefono;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuarios;
    public static volatile SingularAttribute<Usuarios, String> email;

}