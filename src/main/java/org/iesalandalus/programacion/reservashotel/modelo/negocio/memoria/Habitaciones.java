/**
 * @author: Patricia Torres Fernandez
 * Date: 05/02/2024
 * Time: 20:55
 */

package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitaciones implements IHabitaciones {

    //Atributo
    private List<Habitacion> coleccionHabitaciones;

    //Constructor
    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    //Métodos
    public List<Habitacion> get(){
        List<Habitacion> copiaColeccionHabitaciones = new ArrayList<>();
        for (Iterator<Habitacion> i = coleccionHabitaciones.iterator();
             i.hasNext();) {
            if(i.next() instanceof Simple){
                copiaColeccionHabitaciones.add(new Simple((Simple) i.next()));
            }
            else if(i.next() instanceof Doble){
                copiaColeccionHabitaciones.add(new Doble((Doble) i.next()));
            }
            else if(i.next() instanceof Triple){
                copiaColeccionHabitaciones.add(new Triple((Triple) i.next()));
            }
            else {
                copiaColeccionHabitaciones.add(new Suite((Suite) i.next()));
            }
        }
            return copiaColeccionHabitaciones;
    }

    public List<Habitacion> get (TipoHabitacion tipoHabitacion){
        List<Habitacion> copiaColeccionHabitacionesTipo = new ArrayList<>();
        for (Iterator<Habitacion> i = get().iterator();i.hasNext();){
            if(i.next().getClass().isInstance(tipoHabitacion)) {
                if(i.next() instanceof Simple){
                    copiaColeccionHabitacionesTipo.add(new Simple((Simple) i.next()));
                }
                else if(i.next() instanceof Doble){
                    copiaColeccionHabitacionesTipo.add(new Doble((Doble) i.next()));
                }
                else if(i.next() instanceof Triple){
                    copiaColeccionHabitacionesTipo.add(new Triple((Triple) i.next()));
                }
                else {
                    copiaColeccionHabitacionesTipo.add(new Suite((Suite) i.next()));
                }
            }
        }
        return copiaColeccionHabitacionesTipo;
    }
    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    public void insertar(Habitacion habitacion) throws
            OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar " +
                    "una habitación nula.");
        }
        if (!coleccionHabitaciones.contains(habitacion)) {
            if(habitacion instanceof Simple){
                coleccionHabitaciones.add(new Simple((Simple) habitacion));
            }
            else if(habitacion instanceof Doble){
                coleccionHabitaciones.add(new Doble((Doble) habitacion));
            }
            else if(habitacion instanceof Triple){
                coleccionHabitaciones.add(new Triple((Triple) habitacion));
            }
            else {
                coleccionHabitaciones.add(new Suite((Suite) habitacion));
            }
        }
        else {
            throw new OperationNotSupportedException("ERROR: Ya existe una " +
                    "habitación con ese identificador.");
        }
    }

    public Habitacion buscar(Habitacion habitacion){
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            return null;
        } else {
            if(coleccionHabitaciones.get(indice) instanceof Simple){
                return new Simple((Simple) coleccionHabitaciones.get(indice));
            }else if(coleccionHabitaciones.get(indice) instanceof Doble){
                return new Doble((Doble) coleccionHabitaciones.get(indice));
            }else if(coleccionHabitaciones.get(indice) instanceof Triple){
                return new Triple((Triple) coleccionHabitaciones.get(indice));
            }else {
                return new Suite((Suite) coleccionHabitaciones.get(indice));
            }
        }
    }

    public void borrar(Habitacion habitacion) throws
            OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar " +
                    "una habitación nula.");
        }
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe " +
                    "ningún aula con ese nombre.");
        } else {
            coleccionHabitaciones.remove(indice);
        }
    }

}
