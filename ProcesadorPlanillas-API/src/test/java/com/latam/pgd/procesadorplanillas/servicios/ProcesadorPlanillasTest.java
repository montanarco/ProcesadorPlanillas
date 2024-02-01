package com.latam.pgd.procesadorplanillas.servicios;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProcesadorPlanillasTest {

    ProcesadorPlanillas procesadorPlanillas;

    ProveedorMiembrosPlanilla proveedor;

    @Before
    void configuracion() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void mockDependencias(){
        proveedor = mock(ProveedorMiembrosPlanilla.class);
        procesadorPlanillas = new ProcesadorPlanillas(proveedor);
    }

    /**
     * Caso de Prueba 001: valida la exception cuando no hay empleados para procesar
     * @resultado generara una excepcion indicando que no hay empleados
     *
     */
    @Test
    void CP01_processarPlanilla_SinEmpleados() {
        //Arrange: Preparar
        when(this.proveedor.recuperarEmpleados()).thenReturn(new ArrayList <> ());
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesadorPlanillas::processarPlanilla);
        //Assert: Validar
        String esperado = "No existen empleados para procesar";
        assertEquals(esperado, exception.getMessage());
    }

    /**
     * Caso de Prueba 002: valida la exception cuando algun empleado tiene ID invalido
     * @resultado generara una el nombre del empleado que genero la excepcion
     *
     */
    @Test
    void CP02_processarPlanilla_EmpleadoConIdIgualCero() {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(0, "Jairo",  1000.0f, true));
        empleados.add(new Empleado(1, "Miguel",  2000.0f, true));
        when(this.proveedor.recuperarEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesadorPlanillas::processarPlanilla);
        //Assert: Validar
        String esperado = "ID de empleado Jairo inválido (0)";
        assertEquals(esperado, exception.getMessage());
    }
    /**
     * Caso de Prueba 003: valida la exception cuando algun empleado tiene Nombre invalido
     * @resultado generara excepcion indicando el ID del empleado con nombre Invalido
     *
     */
    @Test
    void CP03_processarPlanilla_EmpleadoConNombreVacio() {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "", 1000.0f, true));
        empleados.add(new Empleado(2, "Miguel",  2000.0f, true));
        when(this.proveedor.recuperarEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesadorPlanillas::processarPlanilla);
        //Assert: Validar
        String esperado = "Nombre del empleado con ID 1 vacío";
        assertEquals(esperado, exception.getMessage());
    }
    /**
     * Caso de Prueba 004: valida la exception si el valor de la planilla es negativa
     * @resultado generara excepcion mostando un mensaje
     *
     */
    @Test
    void CP04_processarPlanilla_SumaDeMontosNegativa() throws Exception {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Miguel",  -1000.0f,true));
        empleados.add(new Empleado(1, "Juan"  ,  -2000.0f,true));
        //Act: Ejecutar
        when(this.proveedor.recuperarEmpleados()).thenReturn(empleados);
        //Assert: Validar
        String esperado = "La suma del monto mensual es menor que 0";
        Exception exception = assertThrows(Exception.class, procesadorPlanillas::processarPlanilla);
        assertEquals(esperado, exception.getMessage());
    }

    /**
     * Caso de Prueba 005: que se calcule correctamente el total cuando existen empleados inactivos
     * @resultado genera el resultado de la suma de los empleados activos
     *
     */
    @Test
    void CP05_processarPlanilla_totalizar() throws Exception {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Juan",  1000.0f, true));
        empleados.add(new Empleado(2, "Maria", 2000.0f, true));
        empleados.add(new Empleado(3, "carlos",  3000.0f, false));
        when(this.proveedor.recuperarEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        //Assert: Validar
        assertEquals(3000.0f, procesadorPlanillas.processarPlanilla());
    }

    @After
    public void liberarRecursos(){
        this.procesadorPlanillas = null;
    }

}