package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {

        SUITE("SUITE"),
        SIMPLE("SIMPLE"),
        DOBLE("DOBLE"),
        TRIPLE("TRIPLE");


    //Atributo
    private String cadenaAmostrar;

    //Constructor
    TipoHabitacion(String cadenaAmostrar) {
        this.cadenaAmostrar = cadenaAmostrar;
    }


    //Método to String

    @Override
    public String toString() {
        return String.format("Tipo Habitacion: %s",
                this.cadenaAmostrar);
    }
}
