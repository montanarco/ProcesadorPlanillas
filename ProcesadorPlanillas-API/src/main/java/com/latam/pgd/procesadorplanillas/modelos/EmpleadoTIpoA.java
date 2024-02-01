package com.latam.pgd.procesadorplanillas.modelos;

import lombok.Getter;

public class EmpleadoTIpoA extends Empleado{

    @Getter
    private Float bono; //rate
    public EmpleadoTIpoA(Integer id, String nombre, Float montoMensual, boolean activo) {
        super(id, nombre, montoMensual, activo);
    }

    @Override
    public Float getMontoMensualSalario() {
        return this.getMontoMensualSalario() + this.bono;
    }
}
