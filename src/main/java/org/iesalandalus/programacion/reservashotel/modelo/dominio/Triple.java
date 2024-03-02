/**
 * @author: Patricia Torres Fernandez
 * Date: 01/03/2024
 * Time: 23:39
 */

package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Triple extends Habitacion {

    //Atributos
    private static final int NUM_MAXIMO_PERSONAS=3;
    static final int MIN_NUM_BANOS=2;
    static final int MAX_NUM_BANOS=3;
    static final int MIN_NUM_CAMAS_INDIVIDUALES=2;
    static final int MAX_NUM_CAMAS_INDIVIDUALES=3;
    static final int MIN_NUM_CAMAS_DOBLES=0;
    static final int MAX_NUM_CAMAS_DOBLES=1;
    private int numBanos;
    private int numCamasIndividuales;
    private int numCamasDobles;

    //Constructor

    public Triple (int planta, int puerta, double precio, int numBanos,
                    int numCamasIndividuales, int NumCamasDobles){
        super (planta, puerta, precio);
        setNumBanos (numBanos);
        setNumCamasIndividuales (numCamasIndividuales);
        setNumCamasDobles (numCamasDobles);
        validaNumCamas();
    }

    public Triple (Triple HabitacionTriple){
        super (HabitacionTriple.getPlanta(), HabitacionTriple.getPuerta(),
                HabitacionTriple.getPrecio());
        setNumBanos (HabitacionTriple.getNumBanos());
        setNumCamasIndividuales (HabitacionTriple.getNumCamasIndividuales());
        setNumCamasDobles (HabitacionTriple.getNumCamasDobles());
        validaNumCamas();
    }

    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        if (getNumBanos() < MIN_NUM_BANOS ||
                getNumBanos() > MAX_NUM_BANOS){
        throw new IllegalArgumentException("ERROR: El número de baños no puede ser" +
                " inferior a " + MIN_NUM_BANOS + " ni superior a "
                + Triple.MAX_NUM_BANOS);
        }
        else {
            this.numBanos = numBanos;
        }
    }


    public int getNumCamasIndividuales() {
        return numCamasIndividuales;
    }

    public void setNumCamasIndividuales(int numCamasIndividuales) {
        this.numCamasIndividuales = numCamasIndividuales;
    }

    public int getNumCamasDobles() {
        return numCamasDobles;
    }

    public void setNumCamasDobles(int numCamasDobles) {
        this.numCamasDobles = numCamasDobles;
    }

    private void validaNumCamas() {
        if (getNumCamasIndividuales() < MIN_NUM_CAMAS_INDIVIDUALES ||
                getNumCamasIndividuales() > MAX_NUM_CAMAS_INDIVIDUALES) {
            throw new IllegalArgumentException("ERROR: El número de camas " +
                    "individuales de una habitación triple no puede ser inferior a"
                    + MIN_NUM_CAMAS_INDIVIDUALES + " ni mayor que "
                    + MAX_NUM_CAMAS_INDIVIDUALES);
        }
        if (getNumCamasDobles() < MIN_NUM_CAMAS_DOBLES ||
                getNumCamasDobles() > MAX_NUM_CAMAS_DOBLES) {
            throw new IllegalArgumentException("ERROR: El número de camas dobles " +
                    "de una habitación triple no puede ser inferior a "
                    + MIN_NUM_CAMAS_DOBLES + " ni mayor que "
                    + MAX_NUM_CAMAS_DOBLES);
        }
        if ((getNumCamasIndividuales() != MAX_NUM_CAMAS_INDIVIDUALES &&
                getNumCamasDobles() != MIN_NUM_CAMAS_DOBLES) ||
                (getNumCamasIndividuales() != MIN_NUM_CAMAS_INDIVIDUALES &&
                getNumCamasDobles() != MAX_NUM_CAMAS_DOBLES)){
            throw new IllegalArgumentException("ERROR: La distribución de camas " +
                    "en una habitación triple tiene que ser "
                    + Triple.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y "
                    + Triple.MIN_NUM_CAMAS_DOBLES + " doble o "
                    + Triple.MIN_NUM_CAMAS_INDIVIDUALES + " cama individual y "
                    + Triple.MAX_NUM_CAMAS_DOBLES + " doble");
        }
    }

    @Override
    public int getNumeroMaximoPersonas(){
            return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString(){
            return super.toString() + ","
            + String.format("habitación triple, capacidad=%d personas, " +
            "baños=%d, camas individuales=%d, camas dobles=%d",
            this.getNumeroMaximoPersonas(),this.getNumBanos(),
            this.getNumCamasIndividuales(),this.getNumCamasDobles());
    }
}
