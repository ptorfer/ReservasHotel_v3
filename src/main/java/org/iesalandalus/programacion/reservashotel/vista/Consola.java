/**
 * @author: Patricia Torres Fernandez
 * Date: 13/02/2024
 * Time: 18:57
 */

package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import static org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    //Constructor
    private Consola() {
    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.println("Elige una opción");
            ordinalOpcion = Entrada.entero();
        }
        while (!(ordinalOpcion >= 0 && ordinalOpcion <= (Opcion.values().length - 1)));
        return Opcion.values()[ordinalOpcion];
    }

    public static Huesped leerHuesped() {
        Huesped huesped;
        String nombre;
        String telefono;
        String correo;
        String dni;

        do {
            System.out.println("Indique el nombre del huésped: ");
            nombre = Entrada.cadena();
        }
        while (nombre.trim().isEmpty());

        do {
            System.out.println("Indique el teléfono del huésped: ");
            telefono = Entrada.cadena();
        }
        while (telefono.trim().isEmpty());

        do {
            System.out.println("Indique el correo del huésped: ");
            correo = Entrada.cadena();
        }
        while (correo.trim().isEmpty());

        do {
            System.out.println("Indique el DNI del huésped: ");
            dni = Entrada.cadena();
        }
        while (dni.trim().isEmpty());

        String mensaje = "Introduce la fecha de nacimiento del huésped (%s): ";
        LocalDate fechaNacimiento = leerFecha(mensaje);

        huesped = new Huesped (nombre, telefono, correo, dni, fechaNacimiento);

        return new Huesped(huesped);
    }

    public static Huesped leerClientePorDni(){
        Huesped huesped;
        String nombre = "Nombre Ficticio";
        String telefono = "666666666";
        String correo = "HuspedFicticio@gmail.com";
        String dni;
        LocalDate fechaNacimiento = LocalDate.now();

        do {
            System.out.print("Indique el DNI del huésped: ");
            dni = Entrada.cadena();
        } while (dni.isEmpty());

        huesped = new Huesped(nombre, telefono, correo, dni, fechaNacimiento);

        return new Huesped(huesped);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.printf(mensaje, Huesped.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(),
                        DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA));
                fechaValida = true;
            }
            catch (DateTimeParseException e) {
                fechaValida = false;
            }
        }
        while(!fechaValida);
        return fecha;
    }

    public static LocalDateTime leerFechaHora(String mensaje) {
        LocalDateTime fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.printf(mensaje, Reserva.FORMATO_FECHA_HORA_RESERVA);
                LocalDate fecha1 = leerFecha("Indique la fecha: ");
                System.out.print("Indique la hora: ");
                LocalTime time1 = LocalTime.parse(Entrada.cadena());
                fecha = LocalDateTime.of(fecha1, time1);
                fechaValida = true;
            }
            catch (DateTimeParseException e) {
                fechaValida = false;
            }
        }
        while(!fechaValida);
        return fecha;
    }

    public static Habitacion leerHabitacion() {
        Habitacion habitacion;
        int planta;
        int puerta;
        double precio;
        int camasIndividuales;
        int camasDobles;
        int banos;
        char Jacuzzi;
        boolean tieneJacuzzi;

        do {
            System.out.print("Indique la planta de la habitación: ");
            planta = Entrada.entero();
        }
        while (planta < Habitacion.MIN_NUMERO_PLANTA ||
                planta > Habitacion.MAX_NUMERO_PLANTA);

        do {
            System.out.print("Indique la puerta de la habitación: ");
            puerta = Entrada.entero();
        }
        while (puerta < Habitacion.MIN_NUMERO_PUERTA ||
                puerta > Habitacion.MAX_NUMERO_PUERTA);

        do {
            System.out.print("Indique el precio de la habitación: ");
            precio = Entrada.realDoble();
        }
        while (precio < Habitacion.MIN_PRECIO_HABITACION ||
                precio > Habitacion.MAX_PRECIO_HABITACION);

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        if (tipoHabitacion == SIMPLE) {
            habitacion = new Simple (planta, puerta, precio);
            return new Simple ((Simple) habitacion);
        }

        else if (tipoHabitacion == DOBLE){
            System.out.print("Indique el número de camas (1 - Doble) o " +
                    "(2 - Individuales):");
            System.out.print("Camas individuales (0 ó 2):");
            camasIndividuales = Entrada.entero();
            System.out.print("Camas dobles (0 ó 1):");
            camasDobles = Entrada.entero();

            habitacion = new Doble (planta, puerta, precio, camasIndividuales,
                    camasDobles);
            return new Doble ((Doble) habitacion);
        }
        else if (tipoHabitacion ==TRIPLE){
            System.out.print("Indique el número de camas"
                    + "(2 - Individuales y 1 - Doble) o "
                    + "(3 - Individuales):");
            System.out.print("Camas individuales ( 2 ó 3):");
            camasIndividuales = Entrada.entero();
            System.out.print("Camas dobles (0 ó 1):");
            camasDobles = Entrada.entero();

            System.out.print("Introduce el n�mero de ba�os (2 ó 3):");
            banos = Entrada.entero();

            habitacion = new Triple(planta, puerta, precio, banos,
                    camasIndividuales, camasDobles);

            return new Triple((Triple) habitacion);
        }
        else {

            System.out.print("Indique el número de baños (1 ó 3):");
            banos = Entrada.entero();

            do {
                System.out.print("Desea baño con jacuzzi (S/N):");
                Jacuzzi = Entrada.caracter();
            }
            while (Jacuzzi != 'S' && Jacuzzi != 'N');

            tieneJacuzzi = Jacuzzi == 'S';
            habitacion = new Suite(planta, puerta, precio, banos, tieneJacuzzi);

            return new Suite((Suite) habitacion);
        }

    }

    public static Habitacion leerHabitacionPorIdentificador() {
        Simple habitacion;
        int planta;
        int puerta;
        double precio = 70.0;

        do {
            System.out.print("Indique la planta de la habitación: ");
            planta = Entrada.entero();
        }
        while (planta < Habitacion.MIN_NUMERO_PLANTA ||
                planta > Habitacion.MAX_NUMERO_PLANTA);

        do {
            System.out.print("Indique la puerta de la habitación: ");
            puerta = Entrada.entero();
        }
        while (puerta < Habitacion.MIN_NUMERO_PUERTA ||
                puerta > Habitacion.MAX_NUMERO_PUERTA);

        habitacion = new Simple(planta, puerta, precio);

        return new Simple(habitacion);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        int ordinalHabitacion;
        for (TipoHabitacion habitacion: TipoHabitacion.values()) {
            System.out.println(habitacion.ordinal() + ".- " + habitacion);
        }
        do {
            System.out.println("Elige una opción: ");
            ordinalHabitacion = Entrada.entero();
        }
        while (!(ordinalHabitacion >= 0 && ordinalHabitacion <=
                (TipoHabitacion.values().length - 1)));
        return TipoHabitacion.values()[ordinalHabitacion];
    }

    public static Regimen leerRegimen(){
        int ordinalRegimen;
        for (Regimen regimen: Regimen.values()) {
            System.out.println(regimen.ordinal() + ".- " + regimen);
        }
        do {
            System.out.println("Elige un régimen: ");
            ordinalRegimen = Entrada.entero();
        } while (!(ordinalRegimen >= 0 && ordinalRegimen <=
                (Regimen.values().length - 1)));
        return Regimen.values()[ordinalRegimen];
    }

}





