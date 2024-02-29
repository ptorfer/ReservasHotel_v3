package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {
    ;

    //Atributos
    private String cadenaAmostrar;

    SUITE("SUITE"),

    SIMPLE("SIMPLE"),

    DOBLE("DOBLE"),

    TRIPLE("TRIPLE");




    //Constructor
    TipoHabitacion(String cadenaAmostrar) {
        this.cadenaAmostrar = cadenaAmostrar;
    }


    //Método to String


    @Override
    public String toString() {
        return "TipoHabitacion{" +
                "cadenaAmostrar='" + cadenaAmostrar + '\'' +
                '}';
    }
}
