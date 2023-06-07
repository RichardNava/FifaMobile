package com.app.models;

public enum Posicion {
    POR,DEF,MED,DEL;
    
    public Posicion siguientePosicion(){
        Posicion[] posiciones = Posicion.values();
        int siguienteIndex = this.ordinal() + 1;
        if (siguienteIndex >= posiciones.length) {
            return null;  // Indica que no hay más posiciones disponibles
        }
        return posiciones[ siguienteIndex ]; // Si quisiesemos hacerlo ciclico indicariamos: (this.ordinal()+1) % posiciones.length
    }
    
}
