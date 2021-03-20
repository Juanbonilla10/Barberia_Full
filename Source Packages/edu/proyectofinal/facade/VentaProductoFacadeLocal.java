/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.VentaProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface VentaProductoFacadeLocal {

    void create(VentaProducto ventaProducto);

    void edit(VentaProducto ventaProducto);

    void remove(VentaProducto ventaProducto);

    VentaProducto find(Object id);

    List<VentaProducto> findAll();

    List<VentaProducto> findRange(int[] range);

    int count();

    public VentaProducto eliminarPorId(Integer numeroid);

    public String validaStock(int descripcionProducto);

    public VentaProducto actualizaInvenatrio(int cantidadActulizada, int idProducto);

    



    

    

   
    
}
