package org.example;

public interface ReglaInfeccion {
    boolean infectarCiudad(Ciudad ciudad, int puntosInfeccion);
    void moverCiudad(Ciudad ciudad, Ciudad destino);
}

