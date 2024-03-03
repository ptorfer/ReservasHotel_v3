/**
 * @author: Patricia Torres Fernandez
 * Date: 13/02/2024
 * Time: 17:12
 */

package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion.*;
import static org.iesalandalus.programacion.reservashotel.vista.Consola.*;

public class Vista {

    //Atributos

    private Controlador controlador;

    public Vista () {
        Opcion.setVista(this);
    }

    //Metodos

    public void setControlador (Controlador controlador){
        if(controlador == null){
            throw new NullPointerException("ERROR: El controlador no puede ser"
                    + "nulo.");
        }
        this.controlador=controlador;
    }

    public void comenzar(){
        Opcion opcion;
            do {
                mostrarMenu();
                opcion = elegirOpcion();
                opcion.ejecutar();
            }
            while (opcion != Opcion.SALIR);
    }

    public void terminar(){
        controlador.terminar();
    }

    public void insertarHuesped() {
        String mensaje = "Indique huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            Huesped huesped = leerHuesped();
            controlador.insertar(huesped);
            System.out.println("Huésped indicado correctamente.");
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarHuesped() {
        String mensaje = "Buscar huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHuespedes().size() > 0)) {
                System.out.println("No hay huéspedes dados de alta en el " +
                        "sistema.");
            }
            else {
                Huesped huesped = leerClientePorDni();
                Huesped huesped1 = controlador.buscar(huesped);
                if (huesped1 != null) {
                    System.out.println("El hu�sped buscado es: " + huesped1);
                }
                else {
                    System.out.println("No existe ningún huésped con dicho DNI.");
                }
            }
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void borrarHuesped() {
        String mensaje = "Borrar huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHuespedes().size() > 0)) {
                System.out.println("No hay huéspedes dados de alta en el sistema.");
            }
            else {
                Huesped huesped = leerClientePorDni();
                Huesped huesped1 = controlador.buscar(huesped);
                if (huesped1 == null) {
                    System.out.println("No existe ningún huésped con dicho DNI.");
                }
                else {
                    controlador.borrar(huesped1);
                    System.out.println("Huésped borrado correctamente.");
                }
            }
        } catch (IllegalArgumentException |NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarHuespedes(){
        String mensaje = "Mostrar huéspedes";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        List<Huesped> listaHuespedes = controlador.getHuespedes();
        if (!(listaHuespedes.size() > 0)) {
            listaHuespedes.sort(Comparator.comparing(Huesped::getNombre));
            listaHuespedes.forEach(huesped1 -> System.out.println
                    (huesped1.toString()));
        }
        else {
            System.out.println("No hay huéspedes que mostrar.");
        }
    }

    public void insertarHabitacion() {
        String mensaje = "Insertar habitación";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            Habitacion habitacion = leerHabitacion();
            controlador.insertar(habitacion);
            System.out.println("Habitación insertada correctamente.");
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarHabitacion(){
        String mensaje = "Buscar habitación";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en " +
                        "el sistema.");
            }
            else {
                Habitacion habitacion = leerHabitacionPorIdentificador();
                habitacion = controlador.buscar(habitacion);
                if (habitacion != null) {
                    System.out.println("La habitación buscada es: " + habitacion);
                }
                else {
                    System.out.println("La habitación buscada no existe.");
                }
            }
        }
        catch(IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarHabitacion() {
        String mensaje = "Borrar habitacción";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en el " +
                        "sistema.");
            }
            else {
                Habitacion habitacion = leerHabitacionPorIdentificador();
                habitacion = controlador.buscar(habitacion);
                if (habitacion == null) {
                    System.out.println("No existe la habitación indicada.");
                }
                else {
                    controlador.borrar(habitacion);
                    System.out.println("Habitación borrada correctamente.");
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarHabitaciones(){
        String mensaje = "Mostrar habitaciones";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        List<Habitacion> listaHabitacion = controlador.getHabitaciones();
        if (!(listaHabitacion.size() > 0)) {
            listaHabitacion.sort(Comparator.comparing(Habitacion::
                    getIdentificador));
            listaHabitacion.forEach(habitacion ->
                    System.out.println(habitacion.toString()));

        }
        else {
            System.out.println("No hay habitaciones que mostrar.");
        }
    }

    public void insertarReserva() {
        Reserva reserva;
        Huesped huesped;
        Regimen regimen;
        int numeroPersonas;

        String mensaje = "Insertar reserva";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en el " +
                        "sistema para poder realizar la reserva.");
            } else {
                String mensaje1 = "Consultar disponibilidad";
                System.out.printf("%n%s%n", mensaje1);
                String cadena1 = "%0" + mensaje1.length() + "d%n";
                System.out.println(String.format(cadena1, 0).replace("0",
                        "-"));

                TipoHabitacion tipoHabitacion = leerTipoHabitacion();
                LocalDate fechaInicio = leerFecha("Indique la fecha de inicio de " +
                                "reserva (%s): ");
                LocalDate fechaFin = leerFecha("Indique la fecha de fin de " +
                                "reserva (%s): ");
                Habitacion habitacionDisponible = consultarDisponibilidad(
                        tipoHabitacion, fechaInicio, fechaFin);

                if (habitacionDisponible == null) {
                    System.out.println("El tipo de habitación NO está " +
                            "disponible.");
                }
                else {
                    System.out.println("La habitación: " +
                            habitacionDisponible + ", está disponible.");
                    huesped = leerHuesped();
                    regimen = leerRegimen();

                    System.out.print("Introduce el n�mero de personas: ");
                    numeroPersonas = Entrada.entero();
                    reserva = new Reserva(huesped, habitacionDisponible, regimen, fechaInicio,
                            fechaFin, numeroPersonas);
                    reserva = new Reserva(reserva);
                    reserva.setHabitacion(habitacionDisponible);
                    controlador.insertar(reserva);
                    controlador.insertar(reserva.getHuesped());
                    System.out.println("Reserva insertada correctamente.");
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarReservasHuesped(){
        String mensaje = "Listar reservas huésped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        List<Reserva> listaReservas = controlador.getReservas();
        if (!(listaReservas.size()>0)) {
            try {
                Huesped huesped = leerClientePorDni();
                listarReservas(huesped);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("No hay reservas dadas de alta en el sistema.");
        }
    }

    public void listarReservas(Huesped huesped){

        List<Reserva> listaReservas = controlador.getReservas(huesped);
        if (!(listaReservas.size() > 0)){
            listaReservas.sort(Comparator.comparing(
                    Reserva::getFechaInicioReserva).reversed().thenComparing(
                    (Reserva reserva1) -> reserva1.getHabitacion().
                            getIdentificador()));
            int i = 0;
            for (Reserva reserva : listaReservas) {
                System.out.println(i + ".- " + reserva);
                i++;
            }
        }
        else {
            System.out.println("No hay reservas que listar.");
        }
    }

    public void mostrarReservasTipoHabitacion(){
        String mensaje = "Listar reservas tipo habitación";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        List<Reserva> listaReservas = controlador.getReservas();
        if (!(listaReservas.size()>0)) {
            try {
                TipoHabitacion tipoHabitacion = leerTipoHabitacion();
                listarReservas(tipoHabitacion);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No hay reservas dadas de alta en el sistema.");
        }
    }

    public void comprobarDisponibilidad() {
        String mensaje1 = "Consultar disponibilidad";
        System.out.printf("%n%s%n", mensaje1);
        String cadena1 = "%0" + mensaje1.length() + "d";
        System.out.println(String.format(cadena1, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en el"
                        + " sistema para poder realizar la reserva.");
            } else {
                TipoHabitacion tipoHabitacion = leerTipoHabitacion();
                LocalDate fechaInicio = leerFecha("Introduce la fecha"
                        +" de inicio (%s): ");
                LocalDate fechaFin = leerFecha("Introduce la fecha de "
                        + "fin (%s): ");
                Habitacion habitacionDisponible = consultarDisponibilidad
                        (tipoHabitacion, fechaInicio, fechaFin);
                if (habitacionDisponible == null) {
                    System.out.println("El tipo de habitación solicitado NO está"
                            + " disponible.");
                } else {
                    System.out.println("La habitación: " + habitacionDisponible
                            + ", está disponible.");

                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarReservas(TipoHabitacion tipoHabitacion){
        List<Reserva> listaReservas  = controlador.getReservas(tipoHabitacion);
        if (!(listaReservas.size() > 0)){
            listaReservas.sort(Comparator.comparing(
                    Reserva::getFechaInicioReserva).reversed().thenComparing(
                    (Reserva reserva) -> reserva.getHuesped().getNombre()));
            int i = 0;
            for (Reserva reserva: listaReservas) {
                System.out.println(i + ".- " + reserva);
                i++;
            }
        }
        else {
            System.out.println("No hay reservas que listar.");
        }
    }

    public List<Reserva> getReservasAnulables(List<Reserva> reservasAAnular){
        List<Reserva> reservasAnulables = new ArrayList<>();
        for (Iterator<Reserva> reserva = reservasAAnular.iterator();
             reserva.hasNext();) {
            if(reserva.next().getFechaInicioReserva().isAfter(LocalDate.now())){
                reservasAnulables.add(new Reserva(reserva.next()));
            }
        }
        return reservasAnulables;
    }

    public void anularReserva() {
        String mensaje = "Anular reservas";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        char SiReserva = 'S';
        try {
            if (!(controlador.getReservas().size() > 0)) {
                System.out.println("No hay reservas dadas de alta en el sistema.");
            }
            else {
                Huesped huesped = leerClientePorDni();
                huesped = controlador.buscar(huesped);
                if (huesped != null) {
                    List<Reserva> reservas = controlador.getReservas(huesped);
                    if (!(reservas.size() > 0)) {
                        reservas = getReservasAnulables(reservas);
                        int i = 0;
                        for (Reserva reserva : reservas) {
                            System.out.println(i + ".- " + reserva);
                            i++;
                        }
                        System.out.println("Elija la reserva que desea anular.");
                        int numReserva = Entrada.entero();

                        if (reservas.size() == 1) {
                            System.out.println("Confirma que desea anular la " +
                                    "reserva (S/N)?");
                            SiReserva = Entrada.caracter();
                        }
                        if (SiReserva == 'S') {
                            controlador.borrar(reservas.get(numReserva));
                            System.out.println("Reserva anulada correctamente.");
                        }
                        else {
                            System.out.println("La reserva no ha sido anulada.");
                        }
                    }
                    else {
                        System.out.println("No hay reservas que se puedan anular " +
                                "para dicho huésped.");
                    }
                }
                else {
                    System.out.println("No existe ningún huésped con dicho DNI.");
                }
            }
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarReservas(){
        String mensaje = "Mostrar reservas";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        List<Reserva> listaReservas = controlador.getReservas();
        if (!(listaReservas.size() > 0)) {
            listaReservas.sort(Comparator.comparing(
                    Reserva::getFechaInicioReserva).reversed().thenComparing(
                    (Reserva reserva) -> reserva.getHabitacion().
                            getIdentificador()));
            listaReservas.forEach(reserva -> System.out.println
                    (reserva.toString()));
        }
        else {
            System.out.println("No hay reservas que mostrar.");
        }
    }

    public Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion,
                                               LocalDate fechaInicioReserva,
                                               LocalDate fechaFinReserva){
        try {
            if (tipoHabitacion == null) {
                throw new NullPointerException("ERROR: No se puede consultar" +
                        " la disponibilidad de un tipo de habitaci�n nulo.");
            }
            if (fechaInicioReserva.isBefore(LocalDate.now())) {
                throw new NullPointerException("ERROR: La fecha de inicio " +
                        "no debe ser anterior al d�a de hoy.");
            }
            if (!fechaFinReserva.isAfter(fechaInicioReserva)) {
                throw new NullPointerException("ERROR: La fecha de fin de " +
                        "reserva debe ser posterior a la fecha de inicio de " +
                        "reserva.");
            }

            List<Habitacion> habitacionesTipoSolicitado =
                    controlador.getHabitaciones(tipoHabitacion);

            if (!(habitacionesTipoSolicitado.size() > 0)) {
                return null;
            } else {
                for (Habitacion habitacion : habitacionesTipoSolicitado) {
                    List<Reserva> reservasFuturas = controlador.
                            getReservaFuturas(habitacion);
                    if (!(reservasFuturas.size() > 0)) {
                        return habitacion;
                    } else {
                        reservasFuturas.sort(Comparator.comparing(Reserva::
                                getFechaFinReserva).reversed());
                        if (fechaFinReserva.isBefore(reservasFuturas.get(0).
                                getFechaInicioReserva())) {
                            return habitacion;
                        } else {
                            Habitacion habitacionDisponible = null;
                            boolean tipoHabitacionEncontrada = false;
                            for (int j = 1; j < reservasFuturas.size() &&
                                    !tipoHabitacionEncontrada; j++) {
                                if (reservasFuturas.get(j) != null &&
                                        reservasFuturas.get(j) != null) {
                                    if (fechaInicioReserva.isAfter
                                            (reservasFuturas.get(j).
                                                    getFechaFinReserva())
                                            && fechaFinReserva.isBefore
                                            (reservasFuturas.get(j).
                                                    getFechaInicioReserva())) {
                                        if (tipoHabitacion == SIMPLE) {
                                            habitacionDisponible = new
                                                    Simple((Simple)
                                                    habitacionesTipoSolicitado.
                                                            get(j));
                                        } else if (tipoHabitacion == DOBLE) {
                                            habitacionDisponible = new Doble((Doble)
                                                    habitacionesTipoSolicitado.
                                                            get(j));
                                        } else if (tipoHabitacion == TRIPLE) {
                                            habitacionDisponible = new Triple((Triple)
                                                    habitacionesTipoSolicitado.
                                                            get(j));
                                        } else {
                                            habitacionDisponible = new Suite((Suite)
                                                    habitacionesTipoSolicitado.
                                                            get(j));
                                        }
                                        tipoHabitacionEncontrada = true;
                                    }
                                }
                            }
                            return habitacionDisponible;
                        }

                    }
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void realizarCheckin() {
        String mensaje = "Realizar checkin";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en el " +
                        "sistema. El checkin no se puede realizar.");
            }
            else {
                Huesped huesped = leerClientePorDni();
                huesped = controlador.buscar(huesped);
                if (!(huesped == null)) {
                    List<Reserva> reservas = controlador.getReservas(huesped);
                    if (reservas.size() > 0) {
                        listarReservas(huesped);
                        System.out.println("Elija la reserva a la que " +
                                "desea realizar el checkin.");
                        int numReserva = Entrada.entero();
                        Reserva reserva = reservas.get(numReserva);
                        String mensaje1 = "Introduce la fecha y hora de " +
                                "checkin de la reserva (%s): ";
                        LocalDateTime fechaHora = leerFechaHora(mensaje1);
                        controlador.realizarCheckin(reserva, fechaHora);
                        System.out.println("Checkin realizado correctamente.");
                    }
                    else {
                        System.out.println("No existe ninguna reseva para dicho" +
                                " huésped.");
                    }
                }
                else {
                    System.out.println("No existe ningún huésped con dicho DNI.");
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void realizarCheckOut() {
        String mensaje = "Realizar checkout";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        try {
            if (!(controlador.getHabitaciones().size() > 0)) {
                System.out.println("No hay habitaciones dadas de alta en el " +
                        "sistema. El checkout no se puede realizar.");
            }
            else {
                Huesped huesped = leerClientePorDni();
                huesped = controlador.buscar(huesped);
                if (!(huesped == null)) {
                    List<Reserva> reservas = controlador.getReservas(huesped);
                    if (reservas.size() > 0) {
                        listarReservas(huesped);
                        System.out.println("Indique la reserva a la que " +
                                "desea realizar el checkout.");
                        int numReserva = Entrada.entero();
                        Reserva reserva = reservas.get(numReserva);
                        String mensaje1 = "Indique la fecha y hora de checkout" +
                                " de la reserva (%s): ";
                        LocalDateTime fechaHora = leerFechaHora(mensaje1);
                        controlador.realizarCheckout(reserva, fechaHora);
                        System.out.println("Checkout realizado correctamente.");
                    }
                    else {
                        System.out.println("No existe ninguna reseva para " +
                                "dicho huésped.");
                    }
                }
                else {
                    System.out.println("No existe ningún huésped con dicho DNI.");
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
