package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-16T23:42:04")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Integer> idURol;
    public static volatile SingularAttribute<Rol, String> descripcion;
    public static volatile CollectionAttribute<Rol, Usuarios> usuariosCollection;
    public static volatile SingularAttribute<Rol, Integer> codrol;

}