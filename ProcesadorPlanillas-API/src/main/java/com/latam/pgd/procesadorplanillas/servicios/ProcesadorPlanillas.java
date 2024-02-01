package com.latam.pgd.procesadorplanillas.servicios;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;
import com.latam.pgd.procesadorplanillas.inyectores.Proveedor;

import java.util.List;

public class ProcesadorPlanillas implements Proveedor {

    private ProveedorMiembrosPlanilla svrEmpleados;

    public ProcesadorPlanillas(ProveedorMiembrosPlanilla svrEmpleados) {
        this.svrEmpleados = svrEmpleados;
    }

    @Override
    public List<Empleado> getEmpleados() {
        return this.svrEmpleados.recuperarEmpleados();
    }

    /**
     * Este Metodo suma los montos de los empleados activos
     * @return un numero flotante que representa el total de los salarios
     */
    public Float processarPlanilla() throws Exception {
        List<Empleado> lstEmpleados = getEmpleados();
        float sumaMontos = 0.0f;

        if (lstEmpleados == null || lstEmpleados.isEmpty()) {
            throw new Exception("No existen empleados para procesar");
        }

        for (Empleado empleado : lstEmpleados) {
            if (empleado.getId() == 0) {
                throw new Exception(String.format("ID de empleado %s inválido (0)",empleado.getNombre()));
            }
            if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
                throw new Exception(String.format("Nombre del empleado con ID %s vacío",empleado.getId()));
            }
        }

        sumaMontos = lstEmpleados.stream()
                .filter(Empleado::isActivo) // Filter only active Empleado objects
                .map(Empleado::getMontoMensual) // Map to montoMensual
                .reduce(0.0f, Float::sum);

        if (sumaMontos < 0) {
            throw new Exception("La suma del monto mensual es menor que 0");
        }

        return sumaMontos;
    }
}
