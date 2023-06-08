package com.app.models;

public enum Posicion {
    POR,DEF,MED,DEL;
    // El siguiente método nos permite avanzar a la siguiente posición que tenemos que revisar
    // Esto nos permitirá usar solo una página web (dinámica) para mostrar toda la información por partes
    public Posicion siguientePosicion(){
        // El método 'values()' nos devuelve un Array con las constantes del Enum
        Posicion[] posiciones = Posicion.values();
        // Creo una variable que almacena la siguiente posición
        int siguienteIndex = this.ordinal() + 1; // El método 'ordinal()' nos devuelde la posición que ocupa la constante del enum en el Array (de 0 a length-1)
        if (siguienteIndex >= posiciones.length) { // Revisamos que la posición no se salga de los limites del Array
            return null;  // Indicamos que no hay más posiciones disponibles
        }
        // Devolvemos la posición actual para ir revisando la siguiente información
        return posiciones[ siguienteIndex ]; // * Si quisiesemos hacerlo ciclico indicariamos: (this.ordinal()+1) % posiciones.length
    }
    
}
