package org.example;

import java.util.HashSet;
import java.util.Set;

public class Ciudad {
    private final String nombre;
    private boolean infectada;
    private final double probabilidadCuracion;
    private final Set<Ciudad> ciudadesConectadas;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.infectada = false;
        this.probabilidadCuracion = 0.2; // Probabilidad baja de curación
        this.ciudadesConectadas = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isInfectada() {
        return infectada;
    }

    public void setInfectada(boolean infectada) {
        this.infectada = infectada;
    }

    public double getProbabilidadCuracion() {
        return probabilidadCuracion;
    }

    public Set<Ciudad> getCiudadesConectadas() {
        return ciudadesConectadas;
    }

    public void conectarCiudad(Ciudad ciudad) {
        ciudadesConectadas.add(ciudad);
        ciudad.getCiudadesConectadas().add(this); // Conexión bidireccional
    }

    public void intentarCurarse() {
        if (infectada && Math.random() < probabilidadCuracion) {
            infectada = false;
            System.out.println("🔵 Ciudad " + nombre + " se ha curado.");
        }
    }
}
