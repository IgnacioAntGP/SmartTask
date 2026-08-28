package com.iseg.utils;

import java.util.Scanner;

public class Funcionalidades {
	
	public Funcionalidades() {
		
	}
	
	public void pausar(int milisegundos) {
		try {
		    Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		    return;
		}
	}
	
	// Función para confirmar si el usuario desea continuar o no con otra opción
	public boolean deseaContinuar (Scanner sc) {
		
		char opcion;
		
		do {
			System.out.print("\n¿Desea continuar con otra opción? [S]/[N]: ");
			String entrada = sc.nextLine().trim().toUpperCase();
			if (!entrada.isEmpty()) {
				opcion = entrada.charAt(0); 
				
				if (opcion == 'S') {
					System.out.println("\nCONTINUANDO...");
					return true;
				}
	
				else if (opcion == 'N') {
					System.out.println("'\nSALIENDO...");
					return false;
				}
				
				else {
					System.out.println("\nERROR: Opción no valida...");
				}
			}
			else {
				System.out.println("\nERROR: Opción no valida...");
			}
		} while (true);
	} 
}
