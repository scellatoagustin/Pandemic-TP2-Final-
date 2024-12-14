package org.example;

public class InfeccionSimple implements ReglaInfeccion {

    @Override
    public boolean infectarCiudad(Ciudad ciudad) {
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
