package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JuegoInfeccion {
    private final Map<String, Ciudad> ciudades;
    private final ReglaInfeccion reglaInfeccion;

    public JuegoInfeccion(ReglaInfeccion reglaInfeccion) {
        this.ciudades = new HashMap<>();
        this.reglaInfeccion = reglaInfeccion;
        inicializarCiudades();
        conectarCiudadesPredefinidas();
    }

    private void inicializarCiudades() {
        String[] nombresCiudades = {"New York", "Paris", "Tokyo", "Berlin", "Moscow", "Sydney"};
        for (String nombre : nombresCiudades) {
            ciudades.put(nombre, new Ciudad(nombre));
        }
    }

    private void conectarCiudadesPredefinidas() {
        conectarCiudades("New York", "Paris");
        conectarCiudades("Paris", "Berlin");
        conectarCiudades("Berlin", "Moscow");
        conectarCiudades("Moscow", "Tokyo");
        conectarCiudades("Tokyo", "Sydney");
        conectarCiudades("Sydney", "New York");
    }

    public void conectarCiudades(String ciudad1, String ciudad2) {
        Ciudad c1 = ciudades.get(ciudad1);
        Ciudad c2 = ciudades.get(ciudad2);
        if (c1 != null && c2 != null) {
            c1.conectarCiudad(c2);
            c2.conectarCiudad(c1);
        }
    }

    public void iniciarInfeccion(String nombreCiudadInicial) {
        Ciudad ciudadInicial = ciudades.get(nombreCiudadInicial);
        if (ciudadInicial != null) {
            reglaInfeccion.infectarCiudad(ciudadInicial);
        }
    }

    public void mostrarEstadoCiudades() {
        ciudades.values().forEach(InfeccionUtils::mostrarEstadoCiudad);
    }

    public void mostrarConexionesCiudades() {
        ciudades.values().forEach(InfeccionUtils::mostrarConexionesCiudad);
    }

    public void expandirInfeccion() {
        ciudades.values().stream()
                .filter(Ciudad::isInfectada)
                .forEach(ciudad -> reglaInfeccion.infectarCiudad(ciudad));
    }

    public boolean juegoTerminado() {
        return InfeccionUtils.todasInfectadas(ciudades.values().stream().collect(Collectors.toSet()));
    }

    public boolean quedanCiudadesSanas() {
        return InfeccionUtils.quedanSanas(ciudades.values().stream().collect(Collectors.toSet()));
    }
}



