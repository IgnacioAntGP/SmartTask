package com.iseg.clases;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * La Clase GestorTareas se encarga del manejo de la lógica principal del programa. 
 * Administra las colecciones de Tareas alojadas en el sistema y aplica el 
 * Principio de Responsabilidad Unica (SRP) mediante el aislamiento de la lógica del negocio en esta clase.  
 * Permite el manejo de un CRUD básico: buscar, agregar, marcar como completado, eliminar y ordenar las tareas
 * 
 * @author Ignacio Gonzalez / Instituto ISEG - SENCE
 * @version 1.0
 */

public class GestorTareas {

	// Atributos
	private List<Tarea> listaTareas = new ArrayList<Tarea>();
	
	// Constructores
	public GestorTareas() {
		
	}
	
	// Getters y Setters

	public List<Tarea> getTareas() {
		return listaTareas;
	}

	public void setTareas(List<Tarea> tareas) {
		listaTareas = tareas;
	}
	
	// Metodos operativos
	
	public Tarea buscarTarea(int id) {
		for(Tarea t: listaTareas) {
			if (t.getIdTarea() == id) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Agrega una nueva tarea a la lista principal, validando si el ID no se repite
	 * 
	 * @param t El objeto Tarea (Normal o Urgente) que se desea agregar
	 * @return true si se ha agregado correctamente y false si se repite el ID
	 */
	
	public boolean agregarTarea(Tarea t) {
		if(buscarTarea(t.getIdTarea()) == null) {
			listaTareas.add(t);
			return true;
		}
		return false;
	}
	
	/**
	 * Marca una tarea específica (mediante su ID) como "completado" para el sistema y adaptarlo visualmente 
	 * 
	 * @param id El identificador de la tarea que se desea marcar
	 * @return true si se ha marcado como completa, false si no se ha encontrado la tarea mediante el ID
	 */
	
	public boolean marcarTarea(int id) {
		Tarea t = buscarTarea(id);
		if(t != null) {
			if(t.isCompletado() == false)
			{
				t.setCompletado(true);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina una tarea específica (mediante su ID) 
	 * 
	 * @param id El identificador de la tarea que se desea eliminar
	 * @return true si se ha eliminado con éxito, false si no se ha encontrado la tarea mediante el ID
	 */
	
	public boolean eliminarTarea(int id) {
		Tarea t = buscarTarea(id);
		if(t != null) {
			listaTareas.remove(t);
			return true;
		}
		return false;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas almacenadas en el sistema 
	 * @return Una lista de tipo List de objetos Tarea
	 */
	
	public List<Tarea> obtenerTareas() {
	    return listaTareas;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas Urgentes almacenadas en el sistema 
	 * @return Una lista de tipo ArrayList de objetos TareaUrgente
	 */
	
	public List<TareaUrgente> obtenerTareasUrgentes(){
		List<TareaUrgente> tareasUrgentes = new ArrayList<TareaUrgente>();
		for (Tarea t: listaTareas) {
			if (t instanceof TareaUrgente) {
				tareasUrgentes.add((TareaUrgente)t);
			}
		}
		return tareasUrgentes;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas Normales almacenadas en el sistema
	 * @return Una lista de tipo ArrayList de objetos TareaNormal
	 */
	
	public List<TareaNormal> obtenerTareasNormales(){
		List<TareaNormal> tareasNormales = new ArrayList<TareaNormal>();
		for (Tarea t: listaTareas) {
			if (t instanceof TareaNormal) {
				tareasNormales.add((TareaNormal)t);
			}
		}
		return tareasNormales;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas Urgentes almacenadas en el sistema, ordenadas numéricamente
	 * por nivel de prioridad
	 * @return Una lista de tipo ArrayList de objetos TareaUrgente ordenada por nivel_prioridad
	 */
	
	public List<TareaUrgente> listadoTareasUrgentesOrdenadas(){
		List<TareaUrgente> tareasUrgentesCopia = new ArrayList<TareaUrgente>(obtenerTareasUrgentes());
		Comparator<TareaUrgente> comparator = Comparator
				.comparing(TareaUrgente::getNivelPrioridad);
		
		Collections.sort(tareasUrgentesCopia, comparator);
	
		return tareasUrgentesCopia;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas Normales almacenadas en el sistema, ordenadas alfabéticamente
	 * según su categoria
	 * @return Una lista de tipo ArrayList de objetos TareaNormal ordenada por categoria
	 */
	public List<TareaNormal> listadoTareasNormalesOrdenadas(){
		List<TareaNormal> tareasNormalesCopia = new ArrayList<TareaNormal>(obtenerTareasNormales());
		Comparator<TareaNormal> comparator = Comparator
				.comparing(TareaNormal::getCategoria);
		
		Collections.sort(tareasNormalesCopia, comparator);
	
		return tareasNormalesCopia;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas almacenadas en el sistema, en un orden específico
	 * según su prioridad, isCompletado, nombre (en orden respectivo)
	 * @return Una lista de tipo ArrayList de objetos Tarea ordenada por prioridad, iscompletado, nombre
	 */
	public List<Tarea> listadoTareasOrdenadas(){
		List<Tarea> tareasCopia = new ArrayList<Tarea>(obtenerTareas());
		Comparator<Tarea> comparator = Comparator
				.comparing(Tarea::getPrioridad)
				.thenComparing(Tarea::isCompletado)
				.thenComparing(Tarea::getNombre);
		
		Collections.sort(tareasCopia, comparator);
	
		return tareasCopia;
	}
	
	/**
	 * Busca y retorna la lista completa de Tareas almacenadas en el sistema que se encuentran pendientes
	 * por completar
	 * @return Una lista de tipo ArrayList de objetos Tarea ordenada donde isCompletado es false
	 */
	
	public List<Tarea> obtenerTareasPendientes() {
		List<Tarea> pendientes = new ArrayList<Tarea>();
	    for (Tarea t : listaTareas) {
	        if (!t.isCompletado()) {
	            pendientes.add(t);
	        }
	    }
	    return pendientes;
	}
	
	public boolean estaVacio() {
	    return listaTareas.isEmpty();
	}

	public boolean hayTareasPendientes() {
	    return !obtenerTareasPendientes().isEmpty();
	}
	
	public int generarNuevoId() {
	    int maxId = 0;
	    for (Tarea t : listaTareas) {
	        if (t.getIdTarea() > maxId) {
	            maxId = t.getIdTarea();
	        }
	    }
	    return maxId + 1; 
	}
}
