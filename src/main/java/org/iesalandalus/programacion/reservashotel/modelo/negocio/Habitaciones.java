/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 20:55
 */

package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Habitaciones {

    //Atributo
    private List<Habitacion> coleccionHabitaciones;

    //Constructor
    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    //Métodos
    public List<Habitacion> get() {
        return copiaProfundaHabitaciones();
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> copiaHabitaciones = new ArrayList<>();
        for (Habitacion habitacion : get()) {
            if (!(habitacion == null) && habitacion.getTipoHabitacion().
                    equals(tipoHabitacion)) {
                copiaHabitaciones.add(new Habitacion(habitacion));
            }
        }
        return copiaHabitaciones;
    }

    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> copiaHabitaciones = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitaciones) {
            copiaHabitaciones.add(new Habitacion(habitacion));
        }
        return copiaHabitaciones;
    }

    public void insertar(Habitacion habitacion) throws
            OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar " +
                    "una habitación nula.");
        }
        if (!coleccionHabitaciones.contains(habitacion)) {
            coleccionHabitaciones.add(new Habitacion(habitacion));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una " +
                    "habitaci�n con ese identificador.");
        }
    }

    public Habitacion buscar(Habitacion habitacion){
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            return null;
        } else {
            return new Habitacion(coleccionHabitaciones.get(indice));
        }
    }

    public void borrar(Habitacion habitacion) throws
            OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar " +
                    "una habitación nula.");
        }
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe " +
                    "ning�n aula con ese nombre.");
        } else {
            coleccionHabitaciones.remove(indice);
        }
    }

}
