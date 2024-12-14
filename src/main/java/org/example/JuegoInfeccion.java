package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JuegoInfeccion {
    private final ReglaInfeccion reglaInfeccion;
    private final Map<String, Ciudad> ciudades;

    public JuegoInfeccion(ReglaInfeccion reglaInfeccion) {
        this.reglaInfeccion = reglaInfeccion;
        this.ciudades = new HashMap<>();
        inicializarCiudades();
    }

    private void inicializarCiudades() {
        // Crear ciudades.
        Ciudad newYork = new Ciudad("New York");
        Ciudad paris = new Ciudad("Paris");
        Ciudad tokyo = new Ciudad("Tokyo");
        Ciudad berlin = new Ciudad("Berlin");
        Ciudad moscow = new Ciudad("Moscow");
        Ciudad sydney = new Ciudad("Sydney");

        // Conectar ciudades.
        newYork.conectarCiudad(paris);
        newYork.conectarCiudad(tokyo);
        paris.conectarCiudad(berlin);
        paris.conectarCiudad(moscow);
        tokyo.conectarCiudad(moscow);
        berlin.conectarCiudad(sydney);
        sydney.conectarCiudad(tokyo);

        // Agregar ciudades al mapa.
        ciudades.put(newYork.getNombre(), newYork);
        ciudades.put(paris.getNombre(), paris);
        ciudades.put(tokyo.getNombre(), tokyo);
        ciudades.put(berlin.getNombre(), berlin);
        ciudades.put(moscow.getNombre(), moscow);
        ciudades.put(sydney.getNombre(), sydney);
    }

    public void mostrarEstadoCiudades() {
        ciudades.values().forEach(ciudad ->
                System.out.println("🌆 " + ciudad.getNombre() + ": " + (ciudad.isInfectada() ? "Infectada" : "Sana"))
        );
    }

    public boolean todasLasCiudadesInfectadas() {
        return ciudades.values().stream().allMatch(Ciudad::isInfectada);
    }

    public Ciudad getCiudad(String nombre) {
        return ciudades.get(nombre);
    }

    public String obtenerCiudadesConectadas(Ciudad ciudad) {
        return ciudad.getCiudadesConectadas()
                .stream()
                .map(Ciudad::getNombre)
                .collect(Collectors.joining(", "));
    }
}
