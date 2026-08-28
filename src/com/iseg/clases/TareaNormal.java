package com.iseg.clases;

public class TareaNormal extends Tarea{
	
	// Atributos
	private String categoria;
	
	// Constructores
	public TareaNormal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TareaNormal(int idTarea, String nombre, String prioridad, String descripcion, boolean completado, String categoria) {
		super(idTarea, nombre, prioridad, descripcion, completado);
		this.categoria = categoria;
		// TODO Auto-generated constructor stub
	}
	
	// Getters y Setters
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * {@inheritDoc}
	 * @return String con el texto "NORMAL"
	 */
	@Override
	public String getTipo() {
		return "NORMAL";
	}

	/**
	 * {@inheritDoc}
	 * Incluye la categoría específica y el tipo de tarea (NORMAL).
	 */
	@Override
	public String obtenerTarea() {
		return super.obtenerTarea() +
				"\nCategoria: " + categoria +
				"\nTipo de Tarea: " + getTipo();
	}
	
}
