package org.example;

public class InfeccionPuntos implements ReglaInfeccion {
    @Override
    public boolean infectarCiudad(Ciudad ciudad, int puntosInfeccion) {
        if (!ciudad.isInfectada() && puntosInfeccion >= 3) { // Requiere 3 o más puntos de infección para infectar
            ciudad.setInfectada(true);
            System.out.println("🔴 Infectando ciudad: " + ciudad.getNombre());
            return true;
        }
        return false;
    }

    @Override
    public void moverCiudad(Ciudad ciudad, Ciudad destino) {
        System.out.println("🌍 Moviéndote de " + ciudad.getNombre() + " a " + destino.getNombre());
    }
}


