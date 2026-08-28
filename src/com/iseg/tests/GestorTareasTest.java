package com.iseg.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iseg.clases.GestorTareas;
import com.iseg.clases.Tarea;
import com.iseg.clases.TareaNormal;
import com.iseg.clases.TareaUrgente;

/**
 * Clase de pruebas unitarias con JUnit 5 para validar el correcto
 * funcionamiento de la lógica del GestorTareas
 * @author Ignacio Gonzalez / Instituto ISEG - SENCE
 * @version 1.0
 */

public class GestorTareasTest {

	private GestorTareas gestor;
    private TareaNormal tareaNormal1, tareaNormal2;
    private TareaUrgente tareaUrgente1, tareaUrgente2;
    
    @BeforeEach
    void agregarTareaExitoso() {
        gestor = new GestorTareas();
        tareaNormal1 = new TareaNormal(1, "Hacer la cama", "BAJA", "Ordenar y hacer la cama", false, "Hogar");
        tareaNormal2 = new TareaNormal(2, "Lavar la loza", "MEDIA", "Lavar la loza y dejarla secando", true, "Estudio");
        tareaUrgente1 = new TareaUrgente(3, "Estudiar Java", "ALTA", "Prepararse para entregar el proyecto Java", false, 1);
        tareaUrgente2 = new TareaUrgente(4, "Ver capsulas SENCE", "MEDIA", "Ver capsula siguiente de materia", true, 3);
        
        gestor.agregarTarea(tareaNormal1);
        gestor.agregarTarea(tareaUrgente1);
        gestor.agregarTarea(tareaNormal2);
        gestor.agregarTarea(tareaUrgente2);
    }
    
    @Test
    @DisplayName("Debe agregar una tarea exitosamente cuando el ID es único")
    void testAgregarTareaExitoso() {
        TareaNormal nuevaTarea = new TareaNormal(5, "Comprar pan", "MEDIA", "Ir a comprar pan", false, "Hogar");
        
        boolean resultado = gestor.agregarTarea(nuevaTarea);
        
        assertTrue(resultado, "La tarea debió agregarse correctamente");
        assertEquals(5, gestor.obtenerTareas().size(), "El tamaño de la lista debió incrementar a 5");
    }

    @Test
    @DisplayName("Debe rechazar la adición de una tarea cuando el ID es duplicado")
    void testAgregarTareaIdDuplicado() {
        TareaNormal tareaDuplicada = new TareaNormal(1, "TareaRepetida", "BAJA", "ID existente", true, "Otros");
        
        boolean resultado = gestor.agregarTarea(tareaDuplicada);
        
        assertFalse(resultado, "No se debe agregar una tarea con ID duplicado.");
        assertEquals(4, gestor.obtenerTareas().size(), "El tamaño de la lista no debió cambiar");
    }

    @Test
    @DisplayName("Debe marcar una tarea existente como completada")
    void testMarcarTareaExitoso() {
        boolean resultado = gestor.marcarTarea(1);
        
        assertTrue(resultado, "El marcado debió retornar True o Exito");
        assertTrue(gestor.buscarTarea(1).isCompletado(), "La Tarea ID 1 debió quedar completada");
    }

    @Test
    @DisplayName("Debe retornar false al intentar marcar un ID que no existe o de una Tarea que esté completada")
    void testMarcarTareaInexistente() {
        boolean resultado = gestor.marcarTarea(99);
        
        assertFalse(resultado, "No se puede marcar un el ID porque no existe o está completado");
    }

    @Test
    @DisplayName("Debe eliminar una tarea correctamente por su ID")
    void testEliminarTareaExitoso() {
        boolean resultado = gestor.eliminarTarea(1);
        
        assertTrue(resultado, "La eliminación debió ser exitosa");
        assertEquals(3, gestor.obtenerTareas().size(), "La lista debió quedar con 3 elementos");
        assertNull(gestor.buscarTarea(1), "No debería existir la Tarea de ID 1");
    }
    
    @Test
    @DisplayName("Retornar false al intentar eliminar una Tarea con ID inexistente")
    void testEliminarTareaFallido() {
        boolean resultado = gestor.eliminarTarea(98);
        
        assertFalse(resultado, "La eliminación debió haber fallado");
        assertEquals(4, gestor.obtenerTareas().size(), "La lista debió quedar con 4 elementos");
        assertNotNull(gestor.buscarTarea(1), "Debería existir la Tarea de ID 1");
        assertNotNull(gestor.buscarTarea(2), "Debería existir la Tarea de ID 2");
        assertNotNull(gestor.buscarTarea(3), "Debería existir la Tarea de ID 3");
        assertNotNull(gestor.buscarTarea(4), "Debería existir la Tarea de ID 4");
    }

    @Test
    @DisplayName("Debe generar un ID secuencial correcto basado en el máximo existente")
    void testGenerarNuevoId() {
        int nuevoId = gestor.generarNuevoId();
        
        assertEquals(5, nuevoId, "El nuevo ID debió ser 5");
    }
    
    @Test
    @DisplayName("Debe retornar la lista completa de las Tareas almacenadas en el sistema")
    void mostrarListaTareas() {
        List<Tarea> listaObtenida = gestor.obtenerTareas();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        
        assertEquals(4, listaObtenida.size(), "El gestor debería tener exactamente 4 tareas almacenadas");
        
        assertTrue(listaObtenida.contains(tareaNormal1), "La lista debe contener la tarea normal de ID 1 agregada en el agregarTareaExitoso");
        assertTrue(listaObtenida.contains(tareaUrgente1), "La lista debe contener la tarea urgente de ID 3 agregada en el agregarTareaExitoso");
        assertTrue(listaObtenida.contains(tareaNormal2), "La lista debe contener la tarea normal de ID 2 agregada en el agregarTareaExitoso");
        assertTrue(listaObtenida.contains(tareaUrgente2), "La lista debe contener la tarea urgente de ID 4 agregada en el agregarTareaExitoso");
    }
    
    @Test
    @DisplayName("Debe retornar la lista de las Tareas normales almacenadas en el sistema")
    void mostrarListaTareasNormales() {
        List<TareaNormal> listaObtenida = gestor.obtenerTareasNormales();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        
        assertEquals(2, listaObtenida.size(), "El gestor debería tener exactamente 2 tareas normales almacenadas");
        
        assertTrue(listaObtenida.contains(tareaNormal1), "La lista debe contener la tarea normal de ID 1 agregada en el agregarTareaExitoso");
        assertTrue(listaObtenida.contains(tareaNormal2), "La lista debe contener la tarea normal de ID 2 agregada en el agregarTareaExitoso");
    }
    
    @Test
    @DisplayName("Debe retornar la lista de las Tareas Urgentes almacenadas en el sistema")
    void mostrarListaTareasUrgentes() {
        List<TareaUrgente> listaObtenida = gestor.obtenerTareasUrgentes();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        
        assertEquals(2, listaObtenida.size(), "El gestor debería tener exactamente 2 tareas urgentes almacenadas");
        
        assertTrue(listaObtenida.contains(tareaUrgente1), "La lista debe contener la tarea urgente de ID 3 agregada en el agregarTareaExitoso");
        assertTrue(listaObtenida.contains(tareaUrgente2), "La lista debe contener la tarea urgente de ID 4 agregada en el agregarTareaExitoso");
    }
    
    @Test
    @DisplayName("Debe retornar la lista completa de las Tareas Urgentes almacenadas en el sistema ordenadas numéricamente")
    void mostrarListaTareasUrgentesOrdenadas() {
        List<TareaUrgente> listaObtenida = gestor.listadoTareasUrgentesOrdenadas();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        
        assertEquals(2, listaObtenida.size(), "El gestor debería tener exactamente 2 tareas urgentes almacenadas");
        
        int nivelPrioridadPosicion0 = listaObtenida.get(0).getNivelPrioridad();
        int nivelPrioridadPosicion1 = listaObtenida.get(1).getNivelPrioridad();
        
        assertEquals(1, nivelPrioridadPosicion0, "La tarea con menor nivel de prioridad debe ir primero");
        assertEquals(3, nivelPrioridadPosicion1, "La tarea con mayor nivel de prioridad debe ir segunda");
    }
    
    @Test
    @DisplayName("Debe retornar la lista completa de las Tareas Normales almacenadas en el sistema ordenadas alfabéticamente")
    void listadoTareasNormalesOrdenadas() {
        List<TareaNormal> listaObtenida = gestor.listadoTareasNormalesOrdenadas();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        
        assertEquals(2, listaObtenida.size(), "El gestor debería tener exactamente 2 tareas urgentes almacenadas");
        
        String categoriaPosicion0 = listaObtenida.get(0).getCategoria();
        String categoriaPosicion1 = listaObtenida.get(1).getCategoria();
        
        assertEquals("Estudio", categoriaPosicion0, "La primera tarea debe ser de categoría Estudio");
        assertEquals("Hogar", categoriaPosicion1, "La segunda tarea debe ser de categoría Hogar");
    }
    
    @Test
    @DisplayName("Debe retornar la lista completa de las Tareas almacenadas en el sistema ordenadas por prioridad, si está completada o no y por nombre")
    void listadoTareasOrdenadas() {
        List<Tarea> listaObtenida = gestor.listadoTareasOrdenadas();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        assertEquals(4, listaObtenida.size(), "El gestor debería tener exactamente 4 tareas urgentes almacenadas");
        
        assertEquals(tareaUrgente1, listaObtenida.get(0), "La tarea 'Estudiar Java' (ALTA) debe ir en la primera posición");
        assertEquals(tareaNormal1, listaObtenida.get(1), "La tarea 'Hacer la cama' (BAJA) debe ir en la segunda posición");
        assertEquals(tareaNormal2, listaObtenida.get(2), "La tarea 'Lavar la loza' (MEDIA) debe ir en la tercera posición");
        assertEquals(tareaUrgente2, listaObtenida.get(3), "La tarea 'Ver capsulas' (MEDIA) debe ir en la última posición");
    }
    
    @Test
    @DisplayName("Debe retornar la lista completa de las Tareas almacenadas en el sistema que estén pendientes por hacer")
    void listadoTareasPendientes() {
        List<Tarea> listaObtenida = gestor.obtenerTareasPendientes();
        
        assertNotNull(listaObtenida, "La lista de tareas no debería ser nula");
        assertEquals(2, listaObtenida.size(), "El gestor debería tener exactamente 2 tareas urgentes almacenadas");
        
        assertTrue(listaObtenida.contains(tareaNormal1), "La lista debe contener la Tarea Normal pendiente ID 1");
        assertTrue(listaObtenida.contains(tareaUrgente1), "La lista debe contener la Tarea Urgente pendiente ID 3");
    }
    
    @Test
    @DisplayName("Debe retornar un reporte completo de la Tarea, ya sea NORMAL o URGENTE")
    void mostrarReporteTarea() {
        String reporteNormal = tareaNormal1.obtenerTarea();
        String reporteUrgente = tareaUrgente1.obtenerTarea();
        
        assertNotNull(reporteNormal, "El reporte de la tarea normal no debe ser nulo");
        assertNotNull(reporteUrgente, "El reporte de la tarea urgente no debe ser nulo");
        
        assertTrue(reporteNormal.contains("NORMAL"), "El reporte debe incluir el tipo de tarea NORMAL");
        assertTrue(reporteNormal.contains("Categoria:"), "El reporte debe incluir el atributo específico 'Categoria'");
        
        assertTrue(reporteUrgente.contains("URGENTE"), "El reporte debe incluir el tipo de tarea URGENTE");
        assertTrue(reporteUrgente.contains("Nivel de prioridad:"), "El reporte debe incluir el atributo específico 'Nivel de prioridad'");
    }
    
    @Test
    @DisplayName("Debe retornar un reporte completo de la Tarea, ya sea NORMAL o URGENTE")
    void mostrarResumenReporteTarea() {
    	String resumenNormalPendiente = tareaNormal1.obtenerResumenTarea(); // ID: 1, Completado: false
        String resumenUrgenteCompletada = tareaUrgente2.obtenerResumenTarea(); // ID: 3, Completado: true
        
        assertNotNull(resumenNormalPendiente, "El reporte de la tarea normal no debe ser nulo");
        assertNotNull(resumenUrgenteCompletada, "El reporte de la tarea urgente no debe ser nulo");
        
        assertTrue(resumenNormalPendiente.contains("ID: 1"), "El resumen debe contener el ID 1");
        assertTrue(resumenNormalPendiente.contains("Hacer la cama"), "El resumen debe contener el nombre de la tarea 1");
        assertTrue(resumenNormalPendiente.contains("Pendiente"), "El reporte debe indicar el estado 'Pendiente'");
        assertFalse(resumenNormalPendiente.contains("Completada"), "El reporte NO debe decir 'Completada' si es false");
        
        assertTrue(resumenUrgenteCompletada.contains("ID: 4"), "El resumen debe contener el ID 4");
        assertTrue(resumenUrgenteCompletada.contains("Ver capsulas SENCE"), "El resumen debe contener el nombre de la tarea 4");
        assertTrue(resumenUrgenteCompletada.contains("Completada"), "El reporte debe indicar el estado 'Completada'");
        assertFalse(resumenUrgenteCompletada.contains("Pendiente"), "El reporte NO debe decir 'Pendiente' si es true");
    }
    
    @Test
    @DisplayName("Debe retornar true si la lista de Tareas está vacía y false si tiene tareas agregadas")
    void mostrarIsListaVacia() {
    	GestorTareas gestorTestVacio = new GestorTareas();
    	
    	assertTrue(gestorTestVacio.estaVacio(), "El gestor recién creado debe estar vacío");
        
        assertFalse(gestor.estaVacio(), "El gestor con tareas no debe estar vacío");
    }
    
    @Test
    @DisplayName("Debe retornar true si la lista de Tareas no tiene tareas pendientes y false si tiene tareas pendientes")
    void mostrarIsTareasPendientes() {
    	GestorTareas gestorTestVacio = new GestorTareas();
    	
    	assertFalse(gestorTestVacio.hayTareasPendientes(), "Un gestor vacío NO debe tener tareas pendientes");
    	
    	assertTrue(gestor.hayTareasPendientes(), "El gestor del setUp debe tener tareas pendientes");
    }
}
