package org.example;

import java.util.stream.Collectors;

public class InfeccionSimple implements ReglaInfeccion {
    @Override
    public void infectarCiudad(Ciudad ciudad) {
        if (!ciudad.isInfectada()) {
            ciudad.setInfectada(true);
            System.out.println("🔴 Infectando ciudad: " + ciudad.getNombre());
        }
        ciudad.getCiudadesConectadas().stream()
                .filter(c -> !c.isInfectada())
                .collect(Collectors.toSet())
                .forEach(c -> c.setInfectada(true));
    }
}




