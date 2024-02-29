/**
 * @author: Patricia Torres Fernandez
 * Date: 29/02/2024
 * Time: 20:40
 */

package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Simple extends Habitacion{

    private int NUM_MAXIMO_PERSONAS;

    public Simple (int planta, int puerta, double precio){
    super (planta, puerta, precio);
    }

    public Simple (Simple habitacionSimple){
        super(habitacionSimple.getPlanta(), habitacionSimple.getPuerta(),
                habitacionSimple.getPrecio());
    }


    @Override
    public int getNumeroMaximoPersonas(){
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return super.toString() + ", habitacion simple," +
                " capacidad personas=" + NUM_MAXIMO_PERSONAS;
    }
}
