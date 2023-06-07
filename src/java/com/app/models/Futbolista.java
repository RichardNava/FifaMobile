package com.app.models;

import java.util.Objects;

public class Futbolista {

    private String nombre, apellidos, nacionalidad;
    private Posicion posicion;
    private int edad, defensa, ataque, pase, ritmo, Equipo;
    private boolean seleccionado;

    public Futbolista(String nombre, String apellidos, int edad, String nacionalidad, String posicion,
            int defensa, int ataque, int pase, int ritmo, int idEquipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.posicion = Posicion.valueOf(posicion);
        this.defensa = defensa;
        this.ataque = ataque;
        this.pase = pase;
        this.ritmo = ritmo;
        this.Equipo = idEquipo;
        
    }

    //constructor sin id de equipo
    public Futbolista(String nombre, String apellidos, int edad, String nacionalidad, String posicion,
            int defensa, int ataque, int pase, int ritmo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.posicion = Posicion.valueOf(posicion);
        this.defensa = defensa;
        this.ataque = ataque;
        this.pase = pase;
        this.ritmo = ritmo;

    }

    public int getIdEquipo() {
        return Equipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.Equipo = idEquipo;
    }

    public Futbolista() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = Posicion.valueOf(posicion);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getPase() {
        return pase;
    }

    public void setPase(int pase) {
        this.pase = pase;
    }

    public int getRitmo() {
        return ritmo;
    }

    public void setRitmo(int ritmo) {
        this.ritmo = ritmo;
    }
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    @Override
    public String toString() {
        return "Futbolista{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", nacionalidad=" + nacionalidad + ", posicion=" + posicion + ", edad=" + edad + ", defensa=" + defensa + ", ataque=" + ataque + ", pase=" + pase + ", ritmo=" + ritmo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.nacionalidad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Futbolista other = (Futbolista) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.nacionalidad, other.nacionalidad)) {
            return false;
        }
        return this.posicion == other.posicion;
    }
    
}
