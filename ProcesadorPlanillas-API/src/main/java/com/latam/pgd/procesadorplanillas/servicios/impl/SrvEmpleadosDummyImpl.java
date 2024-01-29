package com.latam.pgd.procesadorplanillas.servicios.impl;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;
import com.latam.pgd.procesadorplanillas.servicios.ProveedorMiembrosPlanilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SrvEmpleadosDummyImpl implements ProveedorMiembrosPlanilla {


    @Override
    public List<Empleado> recuperarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        // Generar 8 empleados con información aleatoria
        for (int i = 1; i <= 8; i++) {
            Integer id = i;
            Random random = new Random();
            String nombre = generarNombreAleatorio(random);
            Float montoMensual = generarMontoMensualAleatorio(random);
            boolean activo = generarActivoAleatorio(random);

            Empleado empleado = new Empleado(id, nombre, montoMensual, activo);
            empleados.add(empleado);
        }
        return empleados;
    }

    private static String generarNombreAleatorio(Random random) {
        String[] nombres = {"Mauricio","Juan", "María", "Pedro", "Luis", "Ana", "Carlos", "Sofía", "Miguel", "Laura", "David"};
        return nombres[random.nextInt(nombres.length)];
    }

    private static Float generarMontoMensualAleatorio(Random random) {
        return 1000.0f + random.nextFloat() * 9000.0f; // Rango de 1000 a 10000
    }

    private static boolean generarActivoAleatorio(Random random) {
        return random.nextBoolean();
    }
}
