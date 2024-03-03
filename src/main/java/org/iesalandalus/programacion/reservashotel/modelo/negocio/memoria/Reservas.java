/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 20:46
 */

package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reservas implements IReservas {

    //Atributos

    private List<Reserva> coleccionReservas;

    public Reservas(){
        this.coleccionReservas = new ArrayList<>();
    }

    //Métodos
    @Override
    public List<Reserva> get() {
        List<Reserva> copiaColeccionReserva = new ArrayList<>();
        for (Reserva coleccionReserva : coleccionReservas) {
            copiaColeccionReserva.add(new Reserva(coleccionReserva));
        }
        return copiaColeccionReserva;
    }

    @Override
    public int getTamano(){
        return coleccionReservas.size();
    }

    @Override
    public void insertar(Reserva reserva) throws IllegalArgumentException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una " +
                    "reserva nula.");
        }
        if (!coleccionReservas.contains(reserva)) {
            coleccionReservas.add(new Reserva(reserva));
        }
        else {
            throw new IllegalArgumentException("ERROR: Ya existe un " +
                    "reserva con el mismo nombre.");
        }
    }

    @Override
    public Reserva buscar (Reserva reserva){
        if (reserva == null){
            throw new NullPointerException("ERROR: No se puede buscar una " +
                    "reserva nula.");
        }
        int indice = coleccionReservas.indexOf(reserva);
        if (indice == -1) {
            return null;
        } else {
            return new Reserva(coleccionReservas.get(indice));
        }
    }

    @Override
    public void borrar(Reserva reserva)throws
            IllegalArgumentException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una " +
                    "reserva nula.");
        }
        int indice = coleccionReservas.indexOf(reserva);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe ninguna " +
                    "reserva con ese nombre.");
        } else {
            coleccionReservas.remove(indice);
        }
    }

    @Override
    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas" +
                    " de un hu�sped nulo.");
        }
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : get()) {
            if (reserva != null && reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;
    }

    @Override
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas" +
                    " de un tipo de habitación nula.");
        }
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Iterator<Reserva> i = get().iterator(); i.hasNext();) {
            if (i.next().getHabitacion().getClass().isInstance(tipoHabitacion)) {
                reservasHabitacion.add(new Reserva(i.next()));
            }
        }
        return reservasHabitacion;
    }

    @Override
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas" +
                    " de una habitaci�n nula.");
        }
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Reserva reserva : get()) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion)) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    @Override
    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        reserva.setCheckIn(fecha);
    }

    @Override
    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        reserva.setCheckOut(fecha);
    }

}
