/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.facade;

import edu.proyectofinal.modelo.Cronograma;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author soporte
 */
@Local
public interface CronogramaFacadeLocal {

    void create(Cronograma cronograma);

    void edit(Cronograma cronograma);

    void remove(Cronograma cronograma);

    Cronograma find(Object id);

    List<Cronograma> findAll();

    List<Cronograma> findRange(int[] range);

    int count();

    public Cronograma eliminarDatos(Integer cronogramaid);
    
}
