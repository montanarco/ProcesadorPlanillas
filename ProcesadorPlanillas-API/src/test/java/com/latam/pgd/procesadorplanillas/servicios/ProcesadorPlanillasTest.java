package com.latam.pgd.procesadorplanillas.servicios;

import com.latam.pgd.procesadorplanillas.inyectores.InyectorProveedorMiembros;
import com.latam.pgd.procesadorplanillas.inyectores.Proveedor;
import com.latam.pgd.procesadorplanillas.inyectores.impl.InyectorEmpleadosDummyImpl;
import com.latam.pgd.procesadorplanillas.modelos.Empleado;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProcesadorPlanillasTest {

    @InjectMocks InyectorProveedorMiembros injectorMock;
    @Mock ProcesadorPlanillas procesadorMock;
    @BeforeClass
    void configuracion() {
        MockitoAnnotations.openMocks(this);
        /*injector = () -> new ProcesadorPlanillas(new ProveedorMiembrosPlanilla() {
            @Override
            public List<Empleado> recuperarEmpleados() {
                System.out.println("Mock Procesador de Plantillas");
                return null;
            }
        });*/
        //injector =  new InyectorEmpleadosDummyImpl();
        //this.procesador = (ProcesadorPlanillas) injector.getProveedor();
    }

    /**
     * Caso de Prueba 001: valida la exception cuando no hay empleados para procesar
     * @resultado generara una excepcion indicando que no hay empleados
     *
     */
    @Test
    void CP01_planillaSinEmpleados() {
        //Arrange: Preparar
        when(this.procesador.getEmpleados()).thenReturn(Collections.emptyList());
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesador::processarPlanilla);
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
    void CP02_planilla_empleado_con_id_igual_cero() {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(0, "Jairo",  1000.0f, true));
        empleados.add(new Empleado(1, "Miguel",  2000.0f, true));
        when(this.procesador.getEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesador::processarPlanilla);
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
    void CP03_planilla_empleado_con_nombre_vacio() {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "", 1000.0f, true));
        empleados.add(new Empleado(2, "Miguel",  2000.0f, true));
        when(this.procesador.getEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        Exception exception = assertThrows(Exception.class, procesador::processarPlanilla);
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
    void CP04_Planilla_la_suma_del_montos_es_negativa() throws Exception {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Miguel",  -1000.0f,true));
        empleados.add(new Empleado(1, "Juan"  ,  -2000.0f,true));
        //Act: Ejecutar
        when(this.procesador.getEmpleados()).thenReturn(empleados);
        //Assert: Validar
        String esperado = "La suma del monto mensual es menor que 0";
        Exception exception = assertThrows(Exception.class, procesador::processarPlanilla);
        assertEquals(esperado, exception.getMessage());
    }

    /**
     * Caso de Prueba 005: que se calcule correctamente el total cuando existen empleados inactivos
     * @resultado genera el resultado de la suma de los empleados activos
     *
     */
    @Test
    void CP05_planilla_totalizar() throws Exception {
        //Arrange: Preparar
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Juan",  1000.0f, true));
        empleados.add(new Empleado(2, "Maria", 2000.0f, true));
        empleados.add(new Empleado(3, "carlos",  3000.0f, false));
        when(this.procesador.getEmpleados()).thenReturn(empleados);
        //Act: Ejecutar
        //Assert: Validar
        assertEquals(3000.0f, procesador.processarPlanilla());
    }

    @After
    public void liberar(){
        this.injector = null;
        this.procesador = null;
    }

}