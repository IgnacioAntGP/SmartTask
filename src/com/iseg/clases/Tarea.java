package com.iseg.clases;

import java.time.LocalDateTime;

/**
 * La Clase Tarea se encarga del manejo de los objetos de tipo Tarea y sus respectivas subclases. 
 * Administra la información de las Tareas alojadas en el sistema.
 * Aplica el Principio de Responsabilidad Unica (SRP) mediante el aislamiento de la obtención de los atributos e instancias de las Tareas.
 * Aplica el Polimorfismo al entregar los atributos básicos para cada tipo de Tarea (Normal y Urgente) y unos métodos para ser sobreescritos.
 * 
 * @author Ignacio Gonzalez / Instituto ISEG - SENCE
 * @version 1.0
 */

public abstract class Tarea implements ITareaAccion{
	
	// Atributos
	protected int idTarea;
	protected String nombre, prioridad, descripcion;
	protected boolean completado;
	protected LocalDateTime fechaCreacion;
	
	// Constructores
	public Tarea() {

	}
	
	/**
	 * Constructor que recibe los parámetros para crear un objeto de tipo Tarea
	 * @param id_tarea int Identificador único de la Tarea
	 * @param nombre String Nombre o título de la tarea
	 * @param prioridad String Nivel de prioridad general (ALTA, MEDIA, BAJA)
	 * @param descripcion String Explicación detallada de la tarea
	 * @param completado boolean Estado inicial de la tarea (true: completada, false: pendiente)
	 */

	public Tarea(int idTarea, String nombre, String prioridad, String descripcion, boolean completado) {
		super();
		this.idTarea = idTarea;
		this.nombre = nombre;
		this.prioridad = prioridad;
		this.descripcion = descripcion;
		this.completado = completado;
		this.fechaCreacion = obtenerFechaSistema();
	}
	
	// Getters y Setters

	public int getIdTarea() {
		return idTarea;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isCompletado() {
		return completado;
	}
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
	/**
	 * Método que permite obtener el tipo de Tarea creada
	 */
	public abstract String getTipo();

	/**
	 * Método que permite obtener la información completa de la Tarea asociada
	 * @return String con la información completa de la Tarea
	 */
	@Override
	public String obtenerTarea() {
		String datosTarea = "\nFecha de creación: " + fechaCreacion +
							"\nNombre: " + nombre +
							"\nPrioridad: " + prioridad + 
							"\nDescripción: " + descripcion +
							"\n¿Está completa?: " + (completado ? "Completada" : "Pendiente");
		return datosTarea;
	}
	
	/**
	 * Método que permite obtener la información resumida de la Tarea asociada en una sola línea de texto
	 * @return String formateado con información básica de la Tarea
	 */
	@Override
	public String obtenerResumenTarea() {
	    return "ID: " + idTarea + " | Nombre: " + nombre + " | Estado: " + (completado ? "Completada" : "Pendiente");
	}

}
