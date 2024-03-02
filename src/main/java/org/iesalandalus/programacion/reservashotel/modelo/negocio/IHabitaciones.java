package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import java.util.List;
public interface IHabitaciones {

    List<Habitacion> get();

    List<Habitacion> get(TipoHabitacion tipoHabitacion);

    int getTamano();
    void insertar (Habitacion habitacion);

    Habitacion buscar (Habitacion habitacion);

    void borrar (Habitacion habitacion);
}
