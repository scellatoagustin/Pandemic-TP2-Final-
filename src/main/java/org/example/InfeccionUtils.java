package org.example;

import java.util.Set;

public class InfeccionUtils {
    public static void mostrarEstadoCiudad(Ciudad ciudad) {
        System.out.println("Ciudad: " + ciudad.getNombre() + ", Estado: " + (ciudad.isInfectada() ? "Infectada" : "Sana"));
    }

    public static void mostrarConexionesCiudad(Ciudad ciudad) {
        System.out.println("🔗 Conexiones de " + ciudad.getNombre() + ":");
        ciudad.getCiudadesConectadas().forEach(c -> {
            System.out.println("  - " + c.getNombre() + " (" + (c.isInfectada() ? "Infectada" : "Sana") + ")");
        });
    }

    public static boolean todasInfectadas(Set<Ciudad> ciudades) {
        return ciudades.stream().allMatch(Ciudad::isInfectada);
    }

    public static boolean quedanSanas(Set<Ciudad> ciudades) {
        return ciudades.stream().anyMatch(ciudad -> !ciudad.isInfectada());
    }
}


