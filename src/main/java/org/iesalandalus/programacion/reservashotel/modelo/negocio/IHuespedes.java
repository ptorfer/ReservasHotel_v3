package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IHuespedes {

    List<Huesped> get();
    int getTamano();
    void insertar (Huesped huesped);
    Huesped buscar (Huesped huesped);
    void borrar (Huesped huesped);
}
