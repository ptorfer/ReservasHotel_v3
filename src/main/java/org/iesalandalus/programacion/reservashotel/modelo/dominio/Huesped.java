package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {

    //Creaci�n de atributos.
    private static final String ER_TELEFONO = "[9876]\\d{8}";
    private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
    private static final String ER_DNI = "(\\d{8})([A-HJ-NP-TV-Z])";
    public static final String FORMATO_FECHA = "dd/mm/yyyy";
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    //Creación del constructor.

    public Huesped(String nombre, String dni, String correo, String telefono,
                   LocalDate fechaNacimiento) {

        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    //Creación constructor copia.
    public Huesped(Huesped huesped){
        if (huesped == null) {
            throw new NullPointerException("ERROR: No es posible copiar un hu�sped nulo.");
        }
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        setFechaNacimiento(huesped.getFechaNacimiento());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = formateaNombre(nombre);
    }

    //Creación método formateaNombre
    private String formateaNombre(String nombre) {

        String palabra = "";
        String resultado = "";

        if (nombre == null)
            throw new NullPointerException("ERROR: El nombre de un hu�sped no puede ser nulo.");

        if (nombre.isEmpty())
            throw new IllegalArgumentException("ERROR: El nombre de un hu�sped no puede estar vac�o.");

        String[] palabras = nombre.trim().toLowerCase().split("\\s+");

        for (int i = 0; i < palabras.length; i++) {

            palabra = palabras[i].trim();

            String letra = palabra.charAt(0) + "";

            resultado = resultado + letra.toUpperCase() + palabra.substring(1, palabra.length()) + " ";
        }

        resultado = resultado.trim();

        return resultado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new IllegalArgumentException("ERROR: El tel�fono de un hu�sped no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El tel�fono del hu�sped no tiene un formato v�lido.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un hu�sped no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del hu�sped no tiene un formato v�lido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un hu�sped no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del hu�sped no tiene un formato v�lido.");
        }
        this.dni = dni;
    }

    //Método comprobarLetraDni.

    private Boolean comprobarLetraDni(String dni) {
        String regex = "^(\\d{8})([A-HJ-NP-TV-Z])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dni);

        if (matcher.matches()) {

            String numero = matcher.group(1);
            String letra = matcher.group(2);

            String letraCalculada = calcularLetraDni(numero);

            if (letra.equals(letraCalculada)) {
                return true; // El DNI es correcto.
            }else{

            }
        }

        return false; // El DNI no es correcto.
    }

    private String calcularLetraDni(String numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int indice = Integer.parseInt(numero) % 23;
        return letras.substring(indice, indice + 1);
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un hu�sped no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    //Creación del método getIniciales.
    private String getIniciales(){
        String [] palabras = nombre.split("");
        StringBuilder iniciales = new StringBuilder();
        for (String palabra : palabras) {
            char inicial = palabra.charAt(0);
            iniciales.append(inicial);
        }
        return iniciales.toString();
    }

    //Creación de los métodos equals y hashCode,

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Huesped huesped = (Huesped) obj;
        return dni.equals(huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("nombre=%s (%s), DNI=%s, correo=%s, tel�fono=%s," +
        " fecha nacimiento=%s", this.nombre, getIniciales(), this.dni, this.correo,
        this.telefono, this.fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }


}

