package org.example;

public class InfeccionSimple implements ReglaInfeccion {


    public boolean intentarInfectar(Ciudad ciudad) {
        if (!ciudad.isInfectada()) {
            ciudad.setInfectada(true);
            System.out.println("🔴 Ciudad " + ciudad.getNombre() + " ha sido infectada.");
            return true;
        } else {
            System.out.println("⚠️ Ciudad " + ciudad.getNombre() + " ya está infectada.");
            return false;
        }
    }
}
