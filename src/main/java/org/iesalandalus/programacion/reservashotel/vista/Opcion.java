package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir"),

    INSERTAR_HUESPED("Insertar huésped"),

    BUSCAR_HUESPED("Buscar huésped"),

    BORRAR_HUESPED("Borrar huésped"),

    MOSTRAR_HUESPEDES("Mostrar huéspedes"),

    INSERTAR_HABITACION("Insertar habitación"),

    BUSCAR_HABITACION("Buscar habitación"),

    BORRAR_HABITACION("Borrar habitación"),

    MOSTRAR_HABITACIONES("Mostrar habitaciones"),

    INSERTAR_RESERVA("Insertar reserva"),

    ANULAR_RESERVA("Anular reserva"),

    MOSTRAR_RESERVAS("Mostrar reservas"),

    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"),

    REALIZAR_CHECKIN("Realizar checkin"),

    REALIZAR_CHECKOUT("Realizar checkout");


    //Atributos

    private String mensajeAMostrar;

    //Constructor

    Opcion (String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    //to String
    @Override
    public String toString(){
        return String.format("%d.-%s.",ordinal(),this.mensajeAMostrar);
    }

}
