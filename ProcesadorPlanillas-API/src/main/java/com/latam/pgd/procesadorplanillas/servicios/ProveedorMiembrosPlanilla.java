package com.latam.pgd.procesadorplanillas.servicios;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;

import java.util.List;

public interface ProveedorMiembrosPlanilla {

    List<Empleado> recuperarEmpleados();
}
