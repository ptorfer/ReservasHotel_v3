/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 20:49
 */

package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes implements IHuespedes {

    //Atributos
    private List<Huesped> coleccionHuespedes;

    //Constructor:

    public Huespedes(){
        this.coleccionHuespedes = new ArrayList<>();
    }

    //Métodos
    @Override
    public List<Huesped> get(){
        List <Huesped> copiaColeccionHuespedes = new ArrayList<>();
        for (Huesped i : coleccionHuespedes) {
            copiaColeccionHuespedes.add(new Huesped(i));
        }
        return copiaColeccionHuespedes;
    }

    @Override
    public int getTamano() {
        return coleccionHuespedes.size();
    }

    @Override
    public void insertar(Huesped huesped) throws IllegalArgumentException {
        if (huesped ==null){
            throw new NullPointerException("ERROR: No se puede insertar " +
                    "un huésped nulo.");
        }
        else if (!coleccionHuespedes.contains(huesped)) {
            coleccionHuespedes.add(new Huesped(huesped));
        }
        else {
            throw new IllegalArgumentException("ERROR: Ya existe un " +
                    "huésped con ese dni.");
        }
    }

    @Override
    public Huesped buscar(Huesped huesped) {
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            return null;
        }
        else {
            return new Huesped(coleccionHuespedes.get(indice));
        }
    }

    @Override
    public void borrar(Huesped huesped) throws IllegalArgumentException {
        if (huesped == null){
            throw new NullPointerException("ERROR: No se puede borrar un "
                    + "hu�sped nulo.");
        }
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe ningún"
                    + " hu�sped como el indicado.");
        }
        else {
            coleccionHuespedes.remove(indice);
        }
    }

}
