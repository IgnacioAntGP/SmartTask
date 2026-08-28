package com.iseg.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.iseg.clases.TareaNormal;

class TareaNormalTest {

    @Test
    @DisplayName("Debe asignar y obtener los valores correctamente (Getters y Setters)")
    void testGettersYSetters() {
        TareaNormal t = new TareaNormal();
        t.setIdTarea(1);
        t.setNombre("Prueba Normal");
        t.setPrioridad("BAJA");
        t.setDescripcion("Desc normal");
        t.setCompletado(false);
        t.setCategoria("Cocina");
        
        assertEquals(1, t.getIdTarea());
        assertEquals("Prueba Normal", t.getNombre());
        assertEquals("BAJA", t.getPrioridad());
        assertEquals("Desc normal", t.getDescripcion());
        assertFalse(t.isCompletado());
        assertEquals("Cocina", t.getCategoria());
        assertEquals("NORMAL", t.getTipo()); // Prueba del método abstracto
    }

    @Test
    @DisplayName("Debe generar los reportes de texto correctamente para Tarea Normal")
    void testReportesTareaNormal() {
        TareaNormal t = new TareaNormal(2, "Lavar loza", "MEDIA", "Lavar la loza y dejar secando", true, "Orden y Limpieza");
        
        String reporteCompleto = t.obtenerTarea();
        String reporteResumido = t.obtenerResumenTarea();
        
        assertTrue(reporteCompleto.contains("NORMAL"));
        assertTrue(reporteCompleto.contains("Categoria: Orden y Limpieza"));
        
        assertTrue(reporteResumido.contains("ID: 2"));
        assertTrue(reporteResumido.contains("Completada"));
        assertFalse(reporteResumido.contains("Pendiente"));
    }
}