package com.latam.pgd.procesadorplanillas.inyectores;

import com.latam.pgd.procesadorplanillas.modelos.Empleado;

import java.util.List;

public interface Proveedor { //Consumer

    List<Empleado> getEmpleados();

}
