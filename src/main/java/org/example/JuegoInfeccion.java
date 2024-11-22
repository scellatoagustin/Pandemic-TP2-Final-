package org.example;

import java.util.HashMap;
import java.util.Map;

public class JuegoInfeccion {
    private final ReglaInfeccion reglaInfeccion;
    private final Map<String, Ciudad> ciudades;
    private int puntosDeInfeccion;

    public JuegoInfeccion(ReglaInfeccion reglaInfeccion) {
        this.reglaInfeccion = reglaInfeccion;
        this.ciudades = new HashMap<>();
        this.puntosDeInfeccion = 3;  // Empezamos con 3 puntos de infección

        // Crear ciudades
        Ciudad newYork = new Ciudad("New York");
        Ciudad paris = new Ciudad("Paris");
        Ciudad tokyo = new Ciudad("Tokyo");
        Ciudad berlin = new Ciudad("Berlin");
        Ciudad moscow = new Ciudad("Moscow");
        Ciudad sydney = new Ciudad("Sydney");

        // Conectar ciudades
        newYork.conectarCiudad(paris);
        newYork.conectarCiudad(tokyo);
        paris.conectarCiudad(berlin);
        paris.conectarCiudad(moscow);
        tokyo.conectarCiudad(moscow);
        berlin.conectarCiudad(sydney);
        sydney.conectarCiudad(tokyo);

        // Agregar ciudades al mapa
        ciudades.put(newYork.getNombre(), newYork);
        ciudades.put(paris.getNombre(), paris);
        ciudades.put(tokyo.getNombre(), tokyo);
        ciudades.put(berlin.getNombre(), berlin);
        ciudades.put(moscow.getNombre(), moscow);
        ciudades.put(sydney.getNombre(), sydney);
    }

    public ReglaInfeccion getReglaInfeccion() {
        return reglaInfeccion;
    }

    public void mostrarEstadoCiudades() {
        ciudades.values().forEach(ciudad -> {
            System.out.println("Ciudad: " + ciudad.getNombre() + ", Estado: " + (ciudad.isInfectada() ? "Infectada" : "Sana"));
        });
    }

    public boolean ciudadExiste(String nombre) {
        return ciudades.containsKey(nombre);
    }

    public void iniciarInfeccion(String ciudadInicial) {
        Ciudad ciudad = ciudades.get(ciudadInicial);
        ciudad.setInfectada(true);
        System.out.println("🔴 La ciudad " + ciudad.getNombre() + " ha sido infectada.");
    }

    public void moverEntreCiudades(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        reglaInfeccion.moverCiudad(ciudadOrigen, ciudadDestino);
    }

    public void ganarPuntoInfeccion() {
        puntosDeInfeccion++;
    }

    public boolean quedanCiudadesSanas() {
        return ciudades.values().stream().anyMatch(ciudad -> !ciudad.isInfectada());
    }

    public boolean juegoTerminado() {
        return ciudades.values().stream().allMatch(Ciudad::isInfectada);
    }

    public Map<String, Ciudad> getCiudades() {
        return ciudades;
    }

    public int getPuntosDeInfeccion() {
        return puntosDeInfeccion;
    }

    public void curarCiudades() {
        if (Math.random() < 0.1 && puntosDeInfeccion > 0) {
            puntosDeInfeccion--;
            ciudades.values().forEach(Ciudad::intentarCurarse);
            System.out.println("🩺 Se ha intentado curar una ciudad.");
        }
    }
}
