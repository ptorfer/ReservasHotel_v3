/**
 * @author: Patricia Torres Fernandez
 * Date: 12/02/2024
 * Time: 21:11
 */

package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Modelo {

    //Atributos
    private IHabitaciones habitaciones;

    private IReservas reservas;

    private IHuespedes huespedes;

    //Constructores

    public Modelo() {
    }

    public void comenzar() {
        this.habitaciones = new Habitaciones();
        this.reservas = new Reservas();
        this.huespedes = new Huespedes();
    }

    public void terminar() {
        System.out.println("Muchas gracias, hasta la próxima.");
    }

    public void insertar(Huesped huesped) throws IllegalArgumentException {
        huespedes.insertar(huesped);
    }

    public Huesped buscar(Huesped huesped) {
        return huespedes.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws IllegalArgumentException{
        huespedes.borrar(huesped);
    }

    public List<Huesped> getHuespedes(){
        return huespedes.get();
    }

    public void insertar(Habitacion habitacion) throws IllegalArgumentException {
        habitaciones.insertar(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {
        return habitaciones.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws IllegalArgumentException{
        habitaciones.borrar(habitacion);
    }

    public List<Habitacion> getHabitaciones(){
        return habitaciones.get();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
        return habitaciones.get(tipoHabitacion);
    }

    public void insertar(Reserva reserva) throws IllegalArgumentException {
        reservas.insertar(reserva);
    }

    public void borrar(Reserva reserva) throws IllegalArgumentException{
        reservas.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva) {
        return reservas.buscar(reserva);
    }

    public List<Reserva> getReservas(){
        return reservas.get();
    }

    public List<Reserva> getReservas(Huesped huesped) {
        return reservas.getReservas(huesped);
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion){
        return reservas.getReservasFuturas(habitacion);
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckIn(reserva,fecha);
    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckOut(reserva,fecha);
    }

}