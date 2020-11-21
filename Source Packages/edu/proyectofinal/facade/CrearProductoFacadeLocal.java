/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CrearProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface CrearProductoFacadeLocal {

    void create(CrearProducto crearProducto);

    void edit(CrearProducto crearProducto);

    void remove(CrearProducto crearProducto);

    CrearProducto find(Object id);

    List<CrearProducto> findAll();

    List<CrearProducto> findRange(int[] range);

    int count();
    
}
