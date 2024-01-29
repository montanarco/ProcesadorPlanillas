package com.latam.pgd.procesadorplanillas.servicios.impl;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;
import com.latam.pgd.procesadorplanillas.servicios.ProveedorMiembrosPlanilla;

import java.util.List;

public class SvrEmpleadosRestImpl implements ProveedorMiembrosPlanilla {
    @Override
    public List<Empleado> recuperarEmpleados() {
        return null; //TODO: se pueden obtener los empleados desde otras fuentes servicios Rest o BD
    }
}
