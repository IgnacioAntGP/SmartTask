package com.iseg.utils;

import java.util.Scanner;

public class Validaciones {

	public Validaciones() {
		// TODO Auto-generated constructor stub
	}
	
	// Función para validar opción dentro de un intervalo
	public boolean isOpcionValida(int opcion, int inferior, int superior) {
		if (opcion >= inferior && opcion <= superior) {
			return true;
		}
		else {
			System.out.println("\nERROR: Opción no válida. Ingrese un número entre las opciones mostradas...");
			return false;
		}
	}
	
	// Función para validar que sólo se ingresen números al Scanner
	public int leerEntero(Scanner sc, String mensaje) {
        int n = 0;
        boolean esValido = false;

        while (!esValido) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                esValido = true;
            } else {
            	System.out.println("\nERROR: DATO NO VALIDO. Ingrese sólo números enteros...");
                sc.next();
            }
            sc.nextLine();
        }
        return n;
    } 

}
