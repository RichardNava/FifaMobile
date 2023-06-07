package com.app.web;

import com.app.dao.DAOFutbolista;
import com.app.models.Futbolista;
import com.app.models.Posicion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("fm")
@ApplicationScoped
public class FutbolistaManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient Map<Posicion, List<Futbolista>> futbolistasPorPosicion;
    private Posicion posicionActual;
    private ArrayList<Futbolista> futbolistasSeleccionados;
    private transient List<Futbolista> futbolistasActuales;

    public FutbolistaManager() {
        // Inicializar la estructura de datos con los futbolistas por posición
        futbolistasPorPosicion = new HashMap<>();
        futbolistasPorPosicion.put(Posicion.POR, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"POR\""));
        futbolistasPorPosicion.put(Posicion.DEF, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"DEF\""));
        futbolistasPorPosicion.put(Posicion.MED, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"MED\""));
        futbolistasPorPosicion.put(Posicion.DEL, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"DEL\""));
        // Inicializar el resto de datos
        posicionActual = Posicion.POR;
        futbolistasSeleccionados = new ArrayList<>();
    }

    public List<Futbolista> getFutbolistasActuales() {
        futbolistasActuales = futbolistasPorPosicion.get(posicionActual);
        return futbolistasActuales;
    }

    public String siguientePosicion() {
        for (Futbolista futbolista : futbolistasActuales) {
            if(futbolista.isSeleccionado()){
                futbolistasSeleccionados.add(futbolista);
            }
        }
        Posicion siguientePosicion = posicionActual.siguientePosicion();
        if (siguientePosicion!=null && futbolistasPorPosicion.containsKey(siguientePosicion)) {
            posicionActual = siguientePosicion;
            return "show";
        }
        else{
            return "resumen";
        }
    }

    public Map<Posicion, List<Futbolista>> getFutbolistasPorPosicion() {
        return futbolistasPorPosicion;
    }

    public void setFutbolistasPorPosicion(Map<Posicion, List<Futbolista>> futbolistasPorPosicion) {
        this.futbolistasPorPosicion = futbolistasPorPosicion;
    }

    public Posicion getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Posicion posicionActual) {
        this.posicionActual = posicionActual;
    }

    public ArrayList<Futbolista> getFutbolistasSeleccionados() {
        return futbolistasSeleccionados;
    }

    public void setFutbolistasSeleccionados(ArrayList<Futbolista> futbolistasSeleccionados) {
        this.futbolistasSeleccionados = futbolistasSeleccionados;
    }
    
}
