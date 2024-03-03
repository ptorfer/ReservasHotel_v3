package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {


    public static void main(String[] args) {
        String mensaje = "Programa para la Gestión de Hoteles IES Al-Ándalus";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0",
                "-"));

        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }

}







