/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.IngresoProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface IngresoProductoFacadeLocal {

    void create(IngresoProducto ingresoProducto);

    void edit(IngresoProducto ingresoProducto);

    void remove(IngresoProducto ingresoProducto);

    IngresoProducto find(Object id);

    List<IngresoProducto> findAll();

    List<IngresoProducto> findRange(int[] range);

    int count();
    
}
