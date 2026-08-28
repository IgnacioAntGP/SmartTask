package com.iseg.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iseg.utils.Funcionalidades;

class FuncionalidadesTest {

    private Funcionalidades f;

    @BeforeEach
    void setUp() {
        f = new Funcionalidades();
    }

    @Test
    @DisplayName("Debe retornar true al ingresar la letra S (sea mayúscula o minúscula)")
    void testDeseaContinuarSi() {
        String entradaSimulada = "s\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(entradaSimulada.getBytes()));
        
        assertTrue(f.deseaContinuar(sc), "Debe retornar true al ingresar 's'");
        sc.close();
    }

    @Test
    @DisplayName("Debe retornar false al ingresar la letra N")
    void testDeseaContinuarNo() {
        String entradaSimulada = "N\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(entradaSimulada.getBytes()));
        
        assertFalse(f.deseaContinuar(sc), "Debe retornar false al ingresar 'N'");
        sc.close();
    }

    @Test
    @DisplayName("Debe manejar entradas inválidas vacías y luego capturar la S")
    void testDeseaContinuarVacio() {
        String entradaSimulada = "\nx\nS\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(entradaSimulada.getBytes()));
        
        assertTrue(f.deseaContinuar(sc), "Debió ignorar el vacío y la 'x', para terminar retornando true con la 'S'");
        sc.close();
    }

    @Test
    @DisplayName("El método pausar no debe arrojar excepciones")
    void testPausar() {
        assertDoesNotThrow(() -> f.pausar(10), "El método pausar no debió arrojar ninguna excepción");
    }
}