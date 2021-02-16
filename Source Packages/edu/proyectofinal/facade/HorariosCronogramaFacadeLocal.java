/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.HorariosCronograma;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface HorariosCronogramaFacadeLocal {

    void create(HorariosCronograma horariosCronograma);

    void edit(HorariosCronograma horariosCronograma);

    void remove(HorariosCronograma horariosCronograma);

    HorariosCronograma find(Object id);

    List<HorariosCronograma> findAll();

    List<HorariosCronograma> findRange(int[] range);

    int count();

    public List<HorariosCronograma> listardisponibles(String horadis);

    public HorariosCronograma actualizaEstadoCita(String horaingreso);
    
}
