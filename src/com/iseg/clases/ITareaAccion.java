package com.iseg.clases;

import java.time.LocalDateTime;

public interface ITareaAccion {
	
	String PRIORIDAD_ALTA = "ALTA";
	String PRIORIDAD_MEDIA = "MEDIA";
	String PRIORIDAD_BAJA = "BAJA";
	
	String obtenerTarea();
	
	String obtenerResumenTarea();
	
	default LocalDateTime obtenerFechaSistema() {
		return LocalDateTime.now();
	}

}
