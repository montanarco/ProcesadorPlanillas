package com.latam.pgd.procesadorplanillas.inyectores.impl;

import com.latam.pgd.procesadorplanillas.inyectores.InyectorProveedorMiembros;
import com.latam.pgd.procesadorplanillas.inyectores.Proveedor;
import com.latam.pgd.procesadorplanillas.servicios.impl.SrvEmpleadosDummyImpl;
import com.latam.pgd.procesadorplanillas.servicios.ProcesadorPlanillas;

public class InyectorEmpleadosDummyImpl implements InyectorProveedorMiembros {
    @Override
    public Proveedor getProveedor() {
        return new ProcesadorPlanillas(new SrvEmpleadosDummyImpl());
    }
}
