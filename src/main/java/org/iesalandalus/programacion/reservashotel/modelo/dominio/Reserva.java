/**
 * @author: Patricia Torres Fernandez
 * Date: 12/02/2024
 * Time: 21:16
 */

package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.security.PrivilegedActionException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.time.Period.between;

public class Reserva {

    //Atributos

    public static final int MAX_NUMERO_MESES_RESERVA = 6;

    public static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;

    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";

    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy hh:mm";

    private Huesped huesped;

    private Habitacion habitacion;

    private Regimen regimen;

    private LocalDate fechaInicioReserva;

    private LocalDate fechaFinReserva;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private double precio;

    private int numeroPersonas;

    //Constructores

    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen,
                   LocalDate fechaInicioReserva, LocalDate fechaFinReserva,
                   int numeroPersonas) {
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setfechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        this.checkIn = null;
        this.checkOut = null;
        setPrecio();
    }

    public Reserva (Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No es posible copiar una " +
                    "reserva nula.");
        }
        setHuesped(reserva.getHuesped());
        setHabitacion(reserva.getHabitacion());
        setRegimen(reserva.getRegimen());
        setFechaInicioReserva(reserva.getFechaInicioReserva());
        setfechaFinReserva(reserva.getFechaFinReserva());
        setNumeroPersonas(reserva.getNumeroPersonas());
        this.checkIn = reserva.getCheckIn();
        this.checkOut = reserva.getCheckOut();
        setPrecio();
    }

    public Huesped getHuesped () {
        return huesped;
    }

    public void setHuesped (Huesped huesped){
        if (huesped == null) {
            throw new NullPointerException("ERROR: El huésped de una reserva no " +
                    "puede ser nulo.");
        }
        this.huesped = huesped;
    }

    public Habitacion getHabitacion (){
        return habitacion;
    }

    public void setHabitacion (Habitacion habitacion) {
        if (habitacion == null){
            throw new NullPointerException("ERROR: La habitación de una reserva " +
                    "no puede ser nula.");
        }
        if (habitacion instanceof Simple){
            this.habitacion = new Simple ((Simple)habitacion);
        }
        else if (habitacion instanceof Doble){
            this.habitacion = new Doble ((Doble)habitacion);
        }
        else if (habitacion instanceof Triple){
            this.habitacion = new Triple ((Triple)habitacion);
        }
        else {
            this.habitacion = new Suite((Suite) habitacion);
        }
    }

    public Regimen getRegimen (){
        return regimen;
    }

    public void setRegimen (Regimen regimen){
        if (regimen == null){
            throw new NullPointerException("ERROR: El régimen de una reserva no " +
                    "puede ser nulo.");
        }
        this.regimen =regimen;
    }

    public LocalDate getFechaInicioReserva(){
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva (LocalDate fechaInicioReserva) {
        if (fechaInicioReserva == null) {
            throw new NullPointerException("ERROR: La fecha de inicio de una " +
                    "reserva no puede ser nula.");
        }
        else if (fechaInicioReserva.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la " +
                    "reserva no puede ser anterior al d�a de hoy.");
        }
        else if (fechaInicioReserva.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la " +
                    "reserva no puede ser posterior a seis meses.");
        }
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva(){
        return fechaFinReserva;
    }

    public void setfechaFinReserva(LocalDate fechaFinReserva) {
        if (fechaFinReserva == null) {
            throw new NullPointerException("ERROR: La fecha de fin de una " +
                    "reserva no puede ser nula.");
        }
        else if (!fechaFinReserva.isAfter(getFechaInicioReserva())) {
            throw new IllegalArgumentException("ERROR: La fecha de fin de la " +
                    "reserva debe ser posterior a la de inicio.");
        }
        this.fechaFinReserva = fechaFinReserva;
    }

    public LocalDateTime getCheckIn(){
        return checkIn;
    }

    public void setCheckIn (LocalDateTime checkIn) {
        if (checkIn == null) {
            throw new NullPointerException("ERROR: El checkin de una reserva " +
                    "no puede ser nulo.");
        }
        if (checkIn.toLocalDate().isBefore(getFechaInicioReserva())) {
            throw new IllegalArgumentException(
                    "ERROR: El checkin de una reserva no puede ser anterior " +
                            "a la fecha de inicio de la reserva.");
        }
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut(){
        return checkOut;
    }

    public void setCheckOut (LocalDateTime checkOut) {
        if (checkOut == null) {
            throw new NullPointerException("ERROR: El checkout de una reserva" +
                    " no puede ser nulo.");
        } else if (checkOut.isBefore(getCheckIn())) {
            throw new IllegalArgumentException(
                    "ERROR: El checkout de una reserva no puede ser anterior " +
                            "al checkin.");
        } else if (checkOut.isAfter(getFechaFinReserva().atStartOfDay()
                .plusHours(MAX_HORAS_POSTERIOR_CHECKOUT))) {
            throw new IllegalArgumentException(
                    "ERROR: El checkout de una reserva puede ser como m�ximo" +
                            " 12 horas despu�s de la fecha de fin de la reserva.");
        }
        this.checkOut = checkOut;
    }

    public double getPrecio(){
        return precio;
    }

    private void setPrecio(){
        Period period = between(getFechaInicioReserva(), getFechaFinReserva());
        this.precio = (habitacion.getPrecio() + regimen.getIncrementoPrecio()) *
                period.getDays();
    }


    public int getNumeroPersonas(){
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas <= 0){
            throw new IllegalArgumentException("ERROR: El número de personas de " +
                    "una reserva no puede ser menor o igual a 0.");
        }
        else if (numeroPersonas > habitacion.getNumeroMaximoPersonas()) {
            throw new IllegalArgumentException("ERROR: El número de personas de" +
                    " una reserva no puede superar al máximo de personas " +
                    "establacidas para el tipo de habitación reservada.");
        }
        this.numeroPersonas = getNumeroPersonas();
    }

    //Métodos equals,hash,toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return habitacion.equals(reserva.habitacion) &&
                fechaInicioReserva.isEqual(reserva.fechaInicioReserva);
    }


    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public String toString() {
        return String.format("Huesped: %s %s Habitación:%s - %s Fecha Inicio " +
                        "Reserva: %s Fecha Fin Reserva: %s Checkin: %s " +
                        "Checkout: %s Precio: %.2f Personas: %d",
                this.huesped.getNombre(), this.huesped.getDni(),
                this.habitacion.getIdentificador(),
                this.fechaInicioReserva.format(DateTimeFormatter.
                        ofPattern(FORMATO_FECHA_RESERVA)),
                this.fechaFinReserva.format(DateTimeFormatter.
                        ofPattern(FORMATO_FECHA_RESERVA)),
                (!(this.checkIn==null))? getCheckIn().format(DateTimeFormatter.
                        ofPattern(FORMATO_FECHA_HORA_RESERVA)):
                        "No registrado",
                (!(this.checkOut==null))? getCheckOut().format(DateTimeFormatter.
                        ofPattern(FORMATO_FECHA_HORA_RESERVA)):
                        "No registrado",
                this.getPrecio(), 1);
    }
}
