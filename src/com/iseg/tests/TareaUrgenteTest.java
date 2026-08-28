package com.iseg.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.iseg.clases.TareaUrgente;

class TareaUrgenteTest {

    @Test
    @DisplayName("Debe asignar y obtener los valores correctamente (Getters y Setters)")
    void testGettersYSetters() {
        TareaUrgente t = new TareaUrgente();
        t.setIdTarea(3);
        t.setNombre("Prueba Urgente");
        t.setPrioridad("ALTA");
        t.setDescripcion("Desc urgente");
        t.setCompletado(true);
        t.setNivelPrioridad(1);
        
        assertEquals(3, t.getIdTarea());
        assertEquals("Prueba Urgente", t.getNombre());
        assertEquals("ALTA", t.getPrioridad());
        assertEquals("Desc urgente", t.getDescripcion());
        assertTrue(t.isCompletado());
        assertEquals(1, t.getNivelPrioridad());
        assertEquals("URGENTE", t.getTipo());
    }

    @Test
    @DisplayName("Debe generar los reportes de texto correctamente para Tarea Urgente")
    void testReportesTareaUrgente() {
        TareaUrgente t = new TareaUrgente(4, "Estudiar Python", "ALTA", "Estudiar para la entrega del proyecto Python", false, 2);
        
        String reporteCompleto = t.obtenerTarea();
        String reporteResumido = t.obtenerResumenTarea();
        
        // Evaluamos el reporte completo (Polimorfismo)
        assertTrue(reporteCompleto.contains("URGENTE"));
        assertTrue(reporteCompleto.contains("Nivel de prioridad: 2"));
        
        // Evaluamos el reporte resumido y el operador ternario
        assertTrue(reporteResumido.contains("ID: 4"));
        assertTrue(reporteResumido.contains("Pendiente"));
        assertFalse(reporteResumido.contains("Completada"));
    }
}