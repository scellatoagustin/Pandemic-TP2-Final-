package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReglaInfeccion reglaInfeccion = new InfeccionSimple();
        JuegoInfeccion juego = new JuegoInfeccion(reglaInfeccion);
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌍 ¡Bienvenido al simulador de infección!");
        System.out.println("\nCiudades disponibles:");
        juego.mostrarEstadoCiudades();

        Ciudad ciudadActual = null;


        while (ciudadActual == null) {
            System.out.print("\nElige la ciudad inicial para comenzar la infección: ");
            String ciudadInicial = scanner.nextLine().trim();
            ciudadActual = juego.getCiudad(ciudadInicial);

            if (ciudadActual == null) {
                System.out.println("⚠️ Ciudad no encontrada. Por favor, ingresa una ciudad válida.");
            }
        }

        reglaInfeccion.infectarCiudad(ciudadActual);

        while (!juego.todasLasCiudadesInfectadas()) {
            System.out.println("\nEstado actual:");
            juego.mostrarEstadoCiudades();

            System.out.println("Desde " + ciudadActual.getNombre() + " puedes moverte a: " + juego.obtenerCiudadesConectadas(ciudadActual));
            System.out.print("\nElige la ciudad destino: ");
            String ciudadDestino = scanner.nextLine().trim();
            Ciudad ciudadSiguiente = juego.getCiudad(ciudadDestino);

            if (ciudadSiguiente != null && ciudadActual.getCiudadesConectadas().contains(ciudadSiguiente)) {
                ciudadActual = ciudadSiguiente;

                System.out.print("¿Intentar infectar la ciudad " + ciudadSiguiente.getNombre() + "? (sí/no): ");
                String respuesta = scanner.nextLine().trim();

                if (respuesta.equalsIgnoreCase("si")) {
                    reglaInfeccion.infectarCiudad(ciudadSiguiente);
                }
            } else {
                System.out.println("⚠️ Movimiento no válido, intenta nuevamente.");
            }
        }

        System.out.println("🦠 Todas las ciudades han sido infectadas.");
        scanner.close();
    }
}
