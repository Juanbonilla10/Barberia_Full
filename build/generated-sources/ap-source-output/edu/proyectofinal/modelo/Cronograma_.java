package edu.proyectofinal.modelo;

import edu.proyectofinal.modelo.CategoriaServicio;
import edu.proyectofinal.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-28T10:31:00")
@StaticMetamodel(Cronograma.class)
public class Cronograma_ { 

    public static volatile SingularAttribute<Cronograma, String> fechaInicio;
    public static volatile SingularAttribute<Cronograma, CategoriaServicio> categoriaservicioidCategoriaServicio;
    public static volatile SingularAttribute<Cronograma, Integer> idCronograma;
    public static volatile SingularAttribute<Cronograma, String> turno;
    public static volatile SingularAttribute<Cronograma, Usuarios> usuariosidUsuarios;

}