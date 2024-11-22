package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReglaInfeccion reglaInfeccion = new InfeccionPuntos();
        JuegoInfeccion juego = new JuegoInfeccion(reglaInfeccion);

        System.out.println("🌍 Bienvenido al simulador de infección de ciudades!");
        System.out.println("Las ciudades disponibles son: New York, Paris, Tokyo, Berlin, Moscow, Sydney.");

        juego.mostrarEstadoCiudades();

        String ciudadActual;
        while (true) {
            System.out.print("\n🦠 Ingresa el nombre de la ciudad inicial de infección: ");
            ciudadActual = scanner.nextLine().trim();
            if (juego.ciudadExiste(ciudadActual)) {
                break;
            }
            System.out.println("Ciudad no válida. Ingresa una ciudad de la lista.");
        }

        juego.iniciarInfeccion(ciudadActual);
        System.out.println("\nEstado inicial:");
        juego.mostrarEstadoCiudades();

        while (juego.quedanCiudadesSanas()) {
            System.out.println("\nPuntos de infección disponibles: " + juego.getPuntosDeInfeccion());

            Ciudad ciudad = juego.getCiudades().get(ciudadActual);
            System.out.println("\nCiudades conectadas a " + ciudadActual + ":");
            ciudad.getCiudadesConectadas().forEach(c ->
                    System.out.println("  - " + c.getNombre() + " (" + (c.isInfectada() ? "Infectada" : "Sana") + ")"));

            System.out.print("\nIngresa la ciudad a la que te quieres mover: ");
            String ciudadDestino = scanner.nextLine().trim();

            if (!juego.ciudadExiste(ciudadDestino) || !ciudad.getCiudadesConectadas().contains(juego.getCiudades().get(ciudadDestino))) {
                System.out.println("Ciudad no válida o no conectada. Intenta nuevamente.");
                continue;
            }

            Ciudad ciudadDestinoObj = juego.getCiudades().get(ciudadDestino);
            juego.moverEntreCiudades(ciudad, ciudadDestinoObj);
            ciudadActual = ciudadDestino; // Actualizar la ciudad actual

            if (juego.getPuntosDeInfeccion() >= 3) {
                System.out.println("\n¿Quieres intentar infectar la ciudad " + ciudadDestino + "? (sí/no)");
                String respuesta = scanner.nextLine().trim();
                if (respuesta.equalsIgnoreCase("si")) {
                    if (reglaInfeccion.infectarCiudad(ciudadDestinoObj, juego.getPuntosDeInfeccion())) {
                        juego.ganarPuntoInfeccion();
                    }
                }
            }

            juego.curarCiudades();

            if (juego.juegoTerminado()) {
                System.out.println("🦠 ¡El juego ha terminado! Todas las ciudades están infectadas.");
                break;
            }
        }

        scanner.close();
    }
}
