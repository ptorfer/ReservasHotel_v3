/**
 * @author: Patricia Torres Fernandez
 * Date: 02/03/2024
 * Time: 01:42
 */

package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Suite extends Habitacion {

    //Atributos
    private static final int NUM_MAXIMO_PERSONAS=4;
    static final int MIN_NUM_BANOS=1;
    static final int MAX_NUM_BANOS=3;
    private int numBanos;
    private boolean tieneJacuzzi;


    //Constructores

    public Suite (int planta, int puerta, double precio, int numBanos,
                  boolean tieneJacuzzi){
        super(planta, puerta, precio);
        setNumBanos (numBanos);
        setTieneJacuzzi(tieneJacuzzi);
    }

    public Suite (Suite HabitacionSuite) {
        super(HabitacionSuite.getPlanta(), HabitacionSuite.getPuerta(),
                HabitacionSuite.getPrecio());
        setNumBanos (HabitacionSuite.getNumBanos());
        setTieneJacuzzi (HabitacionSuite.isTieneJacuzzi());
    }

    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        if (getNumBanos() < MIN_NUM_BANOS ||
                getNumBanos() > MAX_NUM_BANOS){
        throw new IllegalArgumentException("ERROR: El número de baños no puede ser" +
                " inferior a " + MIN_NUM_BANOS + " ni superior a "
                + MAX_NUM_BANOS);
        }
        else{
                this.numBanos=numBanos;
        }
    }

    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return super.toString() + ","
                + String.format("habitación suite, capacidad=%d personas, " +
                "baños=%d, con Jacuzzi, sin Jacuzzi", this.getNumeroMaximoPersonas(),
                this.getNumBanos());

    }

}
