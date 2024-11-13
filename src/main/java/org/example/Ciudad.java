package org.example;

import java.util.HashSet;
import java.util.Set;

public class Ciudad {
    private final String nombre;
    private boolean infectada;
    private final Set<Ciudad> ciudadesConectadas;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.infectada = false;
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

    public Set<Ciudad> getCiudadesConectadas() {
        return ciudadesConectadas;
    }

    public void conectarCiudad(Ciudad ciudad) {
        ciudadesConectadas.add(ciudad);
    }
}


