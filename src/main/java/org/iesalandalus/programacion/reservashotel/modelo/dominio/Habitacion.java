/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 06:38
 */

package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.util.Objects;

public class Habitacion {

    //Atributos

    public static final double MIN_PRECIO_HABITACION = 40.0;

    public static final double MAX_PRECIO_HABITACION = 150.0;

    public static final int MIN_NUMERO_PUERTA = 0;

    public static final int MAX_NUMERO_PUERTA = 14;

    public static final int MIN_NUMERO_PLANTA = 1;

    public static final int MAX_NUMERO_PLANTA = 3;

    private String identificador;

    private int planta;

    private int puerta;

    private double precio;

    private TipoHabitacion tipoHabitacion;

    //Constructores

    public Habitacion(int planta, int puerta, double precio,
                      TipoHabitacion tipoHabitacion){

        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
        setIdentificador(String.valueOf(planta)+puerta);
    }

    public Habitacion(Habitacion habitacion){
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitaci�n nula.");
        }
        setPlanta(habitacion.getPlanta());
        setPuerta(habitacion.getPuerta());
        setPrecio(habitacion.getPrecio());
        setTipoHabitacion(habitacion.getTipoHabitacion());
        setIdentificador(String.valueOf(habitacion.getPlanta())+
                habitacion.getPuerta());
        }

    //M�todos Get y Set
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador){
    this.identificador = identificador;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta){
        if (planta < MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA){
            throw new IllegalArgumentException("ERROR: No se puede establecer " +
                    "como planta de una habitaci�n un valor menor que " +
                    MIN_NUMERO_PLANTA + " ni mayor que " +
                    MAX_NUMERO_PLANTA + ".");
        } else {
            this.planta = planta;

        }
    }

    public int getPuerta() {
        return puerta;
    }

    public void setPuerta(int puerta){
        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer" +
                    " como puerta de una habitaci�n un valor menor que " +
                    MIN_NUMERO_PUERTA + "ni mayor que "+
                    MAX_NUMERO_PUERTA + ".");
        }else {
            this.puerta = puerta;
        }
    }

    public double getPrecio() {
        return precio;
    }

    private void setPrecio (double precio){
        if(precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION){
            throw new IllegalArgumentException("ERROR: No se puede establecer" +
                    " como precio de una habitaci?n un valor menor que " +
                    Habitacion.MIN_PRECIO_HABITACION + " ni mayor que " +
                    Habitacion.MAX_PRECIO_HABITACION + ".");
        }else{
            this.precio = precio;
        }

    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    private void setTipoHabitacion (TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se puede establecer" +
                    " un tipo de habitaci�n nula.");
        }
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Habitacion habitacion = (Habitacion) obj;
        return identificador.equals(habitacion.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio de habitacion=%s, " +
                        "tipo de habitaci�n=%s",
                this.identificador, this.planta, this.puerta,
                this.precio, this.tipoHabitacion);
    }
}
