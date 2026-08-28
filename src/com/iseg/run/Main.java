package com.iseg.run;

import java.util.Scanner;
import com.iseg.utils.Funcionalidades;
import com.iseg.utils.Validaciones;
import com.iseg.clases.*;

public class Main {

	public static void main(String[] args) {
		
		boolean continuarMenu = true, continuarSubMenu = true;
		
		GestorTareas gt = new GestorTareas();
		Funcionalidades f = new Funcionalidades();
		Validaciones v = new Validaciones();
		Scanner sc = new Scanner(System.in);
		
		TareaNormal tn1 = new TareaNormal(1, "Hacer la Cama", "BAJA", "Ordenar y hacer la cama", false, "Hogar");
		TareaNormal tn2 = new TareaNormal(2, "Ordenar la ropa", "MEDIA", "Ordenar la ropa en el closet", true, "Hogar");
		TareaUrgente tu1 = new TareaUrgente(3, "Estudiar Java", "ALTA", "Estudiar para entregar el proyecto", false, 2);
		TareaUrgente tu2 = new TareaUrgente(4, "Hacer CV", "ALTA", "Hacer curriculum para postulaciones", true, 1);
		
		gt.agregarTarea(tn1);
		gt.agregarTarea(tn2);
		gt.agregarTarea(tu1);
		gt.agregarTarea(tu2);
		
		do {
			int opcion = 0;
			
			// Menú principal
			System.out.println("\nGestor de Tareas");
			System.out.println("1. Ver mis tareas");
			System.out.println("2. Agregar nueva tarea");
			System.out.println("3. Marcar tarea como completada");
			System.out.println("4. Eliminar tarea");
			System.out.println("5. SALIR");
			
			opcion = v.leerEntero(sc, "Seleccione una opción: ");

			if(v.isOpcionValida(opcion, 1, 5)) {
				f.pausar(1000);
				switch (opcion) {
					// Menú de Ver mis tareas
					case 1:
						do {
							System.out.println("=== OPCIONES ===");
							System.out.println("1. Mostrar todas las tareas");
							System.out.println("2. Mostrar tareas Urgentes");
							System.out.println("3. Mostrar Tareas Normales");
							System.out.println("4. Volver al menú");
							opcion = v.leerEntero(sc, "Seleccione una opción: ");
							
							if (v.isOpcionValida(opcion, 1, 4)) {
								switch (opcion) {
								case 1:
									System.out.println("\n=== TODAS LAS TAREAS ===");
									for (Tarea t: gt.obtenerTareas()) {
										System.out.println(t.obtenerTarea());
									}
									continuarSubMenu = f.deseaContinuar(sc);
									break;
								case 2:
									System.out.println("\n=== TAREAS URGENTES ===");
									for(TareaUrgente tu: gt.obtenerTareasUrgentes()) {
										System.out.println(tu.obtenerTarea());
									};
									continuarSubMenu = f.deseaContinuar(sc);
									break;
								case 3:
									System.out.println("\n=== TAREAS NORMALES ===");
									for (TareaNormal tn: gt.obtenerTareasNormales()){
										System.out.println(tn.obtenerTarea());
									};
									continuarSubMenu = f.deseaContinuar(sc);
									break;
								case 4:
									System.out.println("\nVolviendo al menú principal...");
									continuarSubMenu = false;
									break;
								default:
									System.out.println("\nERROR: Opción no válida. Intente nuevamente.");
									break;
								}
							}
						} while (continuarSubMenu);
						// Preguntar si desea continuar usando la app
						continuarMenu = f.deseaContinuar(sc);
						break;
					
					// Menú de Agregar tareas
					case 2:						
						do {
							System.out.println("\n=== AGREGAR NUEVA TAREA ===");
							System.out.println("\n1. Agregar Tarea Normal");
							System.out.println("\n2. Agregar Tarea Urgente");
							System.out.println("\n3. Volver al Menú");
							
							opcion = v.leerEntero(sc, "Seleccione una opción: ");
							if (v.isOpcionValida(opcion, 1, 3)) {
								switch (opcion) {
								case 1:
									System.out.println("\n--- DATOS DE LA TAREA NORMAL ---");
									int idNormal = gt.generarNuevoId();
									
									System.out.print("Nombre: ");
									String nombreN = sc.nextLine();
									
									System.out.print("Descripción: ");
									String descN = sc.nextLine();
									
									System.out.print("Prioridad (ALTA/MEDIA/BAJA): ");
									String prioridadN = sc.nextLine().toUpperCase();
									
									System.out.print("Categoría (ej. Trabajo, Hogar, Estudio): ");
									String categoria = sc.nextLine();
									
									boolean isCompletadoN = false;
								
									TareaNormal nuevaNormal = new TareaNormal(idNormal, nombreN, prioridadN, descN, isCompletadoN, categoria);
									
									if (gt.agregarTarea(nuevaNormal)) {
										System.out.println("\n¡Tarea Normal agregada con éxito!");
									} else {
										System.out.println("\nERROR: Ya existe una tarea con el ID " + idNormal);
									}
									continuarSubMenu = f.deseaContinuar(sc);
									break;

								case 2:
									System.out.println("\n--- DATOS DE LA TAREA URGENTE ---");
									int idUrgente = gt.generarNuevoId();
									
									System.out.print("Nombre: ");
									String nombreU = sc.nextLine();
									
									System.out.print("Descripción: ");
									String descU = sc.nextLine();
									
									System.out.print("Prioridad (ALTA/MEDIA/BAJA): ");
									String prioridadU = sc.nextLine().toUpperCase();
									
									int nivelPrioridad = v.leerEntero(sc, "Nivel de Urgencia (1.Muy Urgente, 2.Urgente, 3.Medio): ");
									
									boolean isCompletadoU = false;
									
									TareaUrgente nuevaUrgente = new TareaUrgente(idUrgente, nombreU, prioridadU, descU, isCompletadoU, nivelPrioridad);
									
									if (gt.agregarTarea(nuevaUrgente)) {
										System.out.println("\n¡Tarea Urgente agregada con éxito!");
									} else {
										System.out.println("\nERROR: Ya existe una tarea con el ID " + idUrgente);
									}
									continuarSubMenu = f.deseaContinuar(sc);
									break;

								case 3:
									System.out.println("\nVolviendo al menú principal...");
									continuarSubMenu = false;
									break;
								default:
									System.out.println("\nERROR: Opción no válida. Intente nuevamente.");
									break;
								}
							}
						} while(continuarSubMenu);
						// Preguntar si desea continuar usando la app
						continuarMenu = f.deseaContinuar(sc);
						break;
					// Menú de Marcar completada
					case 3:
						System.out.println("\n=== MARCAR TAREA COMO COMPLETADA ===");

						if (!gt.hayTareasPendientes()) { 
						    System.out.println("No tienes tareas pendientes por completar."); 
						} else {

					        System.out.println("\nTareas pendientes actuales:");
					        for (Tarea t : gt.obtenerTareasPendientes()) {
					            System.out.println(t.obtenerResumenTarea());
					        }
					        System.out.println("--------------------------------------------------");

					        int idMarcar = v.leerEntero(sc, "\nIngrese el ID de la tarea a completar: ");

					        boolean exito = gt.marcarTarea(idMarcar);

					        if (exito) {
					            System.out.println("\n¡Éxito! La tarea con ID " + idMarcar + " ha sido marcada como COMPLETADA");
					        } else {
					            System.out.println("\nERROR: No se encontró ninguna tarea incompleta con el ID " + idMarcar);
					        }
					    }
						// Preguntar si desea continuar usando la app
						continuarMenu = f.deseaContinuar(sc);
						break;
					// Menú de Eliminar tarea					
					case 4:
						System.out.println("\n=== ELIMINAR TAREA ===");

					    if (gt.estaVacio()) {
					        System.out.println("\nNo hay tareas registradas en el sistema para eliminar");
					    } else {
					        System.out.println("\nListado de tareas actuales:");
					        for (Tarea t : gt.obtenerTareas()) {
					            System.out.println(t.obtenerResumenTarea());
					        }
					        System.out.println("--------------------------------------------------");

					        int idEliminar = v.leerEntero(sc, "\nIngrese el ID de la tarea que desea eliminar: ");

					        System.out.println("\nProcesando...");

					        boolean exito = gt.eliminarTarea(idEliminar);

					        if (exito) {
					            System.out.println("\n¡Éxito! La tarea con ID " + idEliminar + " ha sido eliminada permanentemente del sistema.");
					        } else {
					            System.out.println("\nERROR: No se encontró ninguna tarea con el ID " + idEliminar + ".");
					        }
					    }
					 // Preguntar si desea continuar usando la app
						continuarMenu = f.deseaContinuar(sc);
						break;
					
					case 5:
						System.out.println("\nSALIENDO DEL SISTEMA...");
						f.pausar(1000);
						// Preguntar si desea continuar usando la app
						continuarMenu = false;
						break;
					}
				}
			} while (continuarMenu);
		sc.close();
	}
}
