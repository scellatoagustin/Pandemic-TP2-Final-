package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReglaInfeccion reglaInfeccion = new InfeccionSimple();
        JuegoInfeccion juego = new JuegoInfeccion(reglaInfeccion);

        System.out.println("🌍 Bienvenido al simulador de infección de ciudades!");
        System.out.println("Las ciudades disponibles son: New York, Paris, Tokyo, Berlin, Moscow, Sydney.");

        juego.mostrarConexionesCiudades();

        System.out.print("\n🦠 Ingresa el nombre de la ciudad inicial de infección: ");
        String ciudadInicial = scanner.nextLine();
        juego.iniciarInfeccion(ciudadInicial);

        System.out.println("\nEstado inicial:");
        juego.mostrarEstadoCiudades();

        while (juego.quedanCiudadesSanas()) {
            System.out.print("\n¿Deseas expandir la infección? (S/N): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s")) {
                System.out.println("Fin de la simulación. Gracias por jugar!");
                break;
            }

            juego.expandirInfeccion();
            System.out.println("\nEstado después de la expansión:");
            juego.mostrarEstadoCiudades();

            if (juego.juegoTerminado()) {
                System.out.println("\n🎉 ¡Todas las ciudades han sido infectadas! Fin de la simulación.");
                break;
            }
        }

        if (!juego.quedanCiudadesSanas()) {
            System.out.println("\n🏆 ¡Felicidades! Lograste infectar todas las ciudades.");
        } else {
            System.out.println("\nJuego terminado.");
        }

        scanner.close();
    }
}

