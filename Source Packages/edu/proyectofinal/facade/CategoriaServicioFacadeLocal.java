/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CategoriaServicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface CategoriaServicioFacadeLocal {

    void create(CategoriaServicio categoriaServicio);

    void edit(CategoriaServicio categoriaServicio);

    void remove(CategoriaServicio categoriaServicio);

    CategoriaServicio find(Object id);

    List<CategoriaServicio> findAll();

    List<CategoriaServicio> findRange(int[] range);

    int count();
    
}
