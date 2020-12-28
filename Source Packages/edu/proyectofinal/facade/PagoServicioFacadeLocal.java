/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.PagoServicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface PagoServicioFacadeLocal {

    void create(PagoServicio pagoServicio);

    void edit(PagoServicio pagoServicio);

    void remove(PagoServicio pagoServicio);

    PagoServicio find(Object id);

    List<PagoServicio> findAll();

    List<PagoServicio> findRange(int[] range);

    int count();

    public List<PagoServicio> listaPagoB(int usuariosidUsuarios);
    
}
