/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 20:49
 */

package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {

    //Atributos
    private List<Huesped> coleccionHuespedes;

    //Constructor:

    public Huespedes(){
        this.coleccionHuespedes = new ArrayList<>();
    }

    //Métodos
    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    private List<Huesped> copiaProfundaHuespedes(){
        List <Huesped> copiaHuespedes = new ArrayList<>();
        for (Huesped huesped : coleccionHuespedes) {
            copiaHuespedes.add(new Huesped(huesped));
        }
        return copiaHuespedes;
    }

    public int getTamano() {
        return coleccionHuespedes.size();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped ==null){
            throw new NullPointerException("ERROR: No se puede insertar " +
                    "un hu�sped nulo.");
        }
        else if (!coleccionHuespedes.contains(huesped)) {
            coleccionHuespedes.add(new Huesped(huesped));
        }
        else {
            throw new OperationNotSupportedException("ERROR: Ya existe un " +
                    "hu�sped con ese dni.");
        }
    }

    public Huesped buscar(Huesped huesped) {
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            return null;
        }
        else {
            return new Huesped(coleccionHuespedes.get(indice));
        }
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null){
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped " +
                    "como el indicado.");
        }
        else {
            coleccionHuespedes.remove(indice);
        }
    }

}
