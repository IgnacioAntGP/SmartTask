package com.iseg.clases;

public class TareaUrgente extends Tarea {
	
	// Atributos
	private int nivelPrioridad;
	
	// Constructores
	public TareaUrgente() {
	}

	public TareaUrgente(int idTarea, String nombre, String prioridad, String descripcion, boolean completado, int nivel_prioridad) {
		super(idTarea, nombre, prioridad, descripcion, completado);
		this.nivelPrioridad = nivel_prioridad;
	}
	
	// Getters y Setters
	
	public int getNivelPrioridad() {
		return nivelPrioridad;
	}

	public void setNivelPrioridad(int nivelPrioridad) {
		this.nivelPrioridad = nivelPrioridad;
	}
	
	/**
	 * {@inheritDoc}
	 * @return String con el texto "URGENTE"
	 */
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "URGENTE";
	}
	
	/**
	 * {@inheritDoc}
	 * Incluye el nivel de prioridad específico y el tipo de tarea (URGENTE).
	 */
	@Override
	public String obtenerTarea() {
		return super.obtenerTarea() +
				"\nNivel de prioridad: " + nivelPrioridad +
				"\nTipo de Tarea: " + getTipo();
	}

}