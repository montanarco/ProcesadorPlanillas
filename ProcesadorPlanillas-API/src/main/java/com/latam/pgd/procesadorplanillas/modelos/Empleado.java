package com.latam.pgd.procesadorplanillas.modelos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Empleado {

    /** ID (integer) */
    private Integer id;
    /** Nombre (string) */
    private String nombre;
    /** MontoMensual salario mensial del empleado (float)*/
    private Float montoMensual;
    /** Activo (bool) */
    private boolean activo;

    public Empleado(Integer id, String nombre, Float montoMensual, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.montoMensual = montoMensual;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", montoMensual=" + montoMensual +
                ", activo=" + activo +
                '}';
    }


    public Float getMontoMensualSalario() {
        return this.montoMensual;
    }

}
