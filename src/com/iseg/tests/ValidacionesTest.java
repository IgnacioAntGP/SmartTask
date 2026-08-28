package com.iseg.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iseg.utils.Validaciones;

class ValidacionesTest {

    private Validaciones v;

    @BeforeEach
    void setUp() {
        v = new Validaciones();
    }

    @Test
    @DisplayName("Debe retornar true si la opción está en los límites, y false si no")
    void testIsOpcionValida() {
        // Límite entre 1 y 5
        assertTrue(v.isOpcionValida(3, 1, 5), "El 3 es válido entre 1 y 5");
        assertTrue(v.isOpcionValida(1, 1, 5), "El 1 es válido (límite inferior)");
        assertTrue(v.isOpcionValida(5, 1, 5), "El 5 es válido (límite superior)");
        
        assertFalse(v.isOpcionValida(0, 1, 5), "El 0 no es válido");
        assertFalse(v.isOpcionValida(6, 1, 5), "El 6 no es válido");
    }

    @Test
    @DisplayName("Debe leer un entero correctamente incluso si primero se ingresa texto inválido")
    void testLeerEntero() {
        String entradaSimulada = "hola\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(entradaSimulada.getBytes());
        Scanner sc = new Scanner(in);
        
        int resultado = v.leerEntero(sc, "Ingrese un número: ");
        
        assertEquals(5, resultado, "El método debió ignorar 'hola' y capturar el 5");
        sc.close();
    }
}