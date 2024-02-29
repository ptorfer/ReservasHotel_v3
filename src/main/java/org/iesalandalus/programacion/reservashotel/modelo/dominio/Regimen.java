package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {

    SOLO_ALOJAMIENTO("SOLO_ALOJAMIENTO", 0),

    ALOJAMIENTO_DESAYUNO("ALOJAMIENTO_DESAYUNO",15),

    MEDIA_PENSION("MEDIA_PENSION",30),

    PENSION_COMPLETA("PENSION_COMPLETA",50);

    //Atributos

    private String descripcion;

    private double incrementoPrecio;

    //Constructor
    Regimen(String descripcion, double incrementoPrecio){
        this.descripcion = descripcion;
        this.incrementoPrecio = incrementoPrecio;
    }

    //Métodos
    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return String.format("Régimen:%s, Incremento de precio: %s",
                this.descripcion, getIncrementoPrecio());
    }
}
