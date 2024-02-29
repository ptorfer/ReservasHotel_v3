package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {

    SUITE("SUITE", 4),

    SIMPLE("SIMPLE",1),

    DOBLE("DOBLE",2),

    TRIPLE("TRIPLE",3);


    //Atributos
    private String descripcion;
    private int numeroMaximoPersonas;

    //Constructor
    TipoHabitacion(String descripcion,int numeroMaximoPersonas) {
        this.descripcion = descripcion;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
    }

    //Método Get
    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    //Método to String

    @Override
    public String toString() {
        return String.format("Tipo de Habitación:%s, número de personas: %s",
        this.descripcion, getNumeroMaximoPersonas());
    }
}
