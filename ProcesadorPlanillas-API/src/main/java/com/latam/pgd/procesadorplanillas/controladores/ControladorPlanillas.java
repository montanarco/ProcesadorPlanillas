package com.latam.pgd.procesadorplanillas.controladores;

import com.latam.pgd.procesadorplanillas.inyectores.impl.InyectorEmpleadosDummyImpl;
import com.latam.pgd.procesadorplanillas.inyectores.impl.InyectorEmpleadosRestImpl;
import com.latam.pgd.procesadorplanillas.inyectores.InyectorProveedorMiembros;
import com.latam.pgd.procesadorplanillas.servicios.ProcesadorPlanillas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPlanillas {

    ProcesadorPlanillas svrEmpleados = null;

    @GetMapping("/health")
    public String healthCheck(){
        return "la aplicacion se ejecuta correctamente";
    }

    @GetMapping("/procesarPlanillaDummy")
    public String procesarPlanillaDummy(){
        try {
            InyectorProveedorMiembros injectorDummy =  new InyectorEmpleadosDummyImpl();
            svrEmpleados = (ProcesadorPlanillas) injectorDummy.getProveedor();
            Float total = svrEmpleados.processarPlanilla();
            return String.format("{\"Total Planilla\":\"%f\"}", total);
        } catch (Exception e) {
            return "problemas generados al Procesar la planilla";
        }

    }

    @GetMapping("/procesarPlanillaRest")
    public String procesarPlanillaRest(){
        try {
            InyectorProveedorMiembros injectorRest =  new InyectorEmpleadosRestImpl();
            svrEmpleados = (ProcesadorPlanillas) injectorRest.getProveedor();
            float total = svrEmpleados.processarPlanilla();
            return String.format("{\"Total Planilla\":\"%f\"}", total);
        } catch (Exception e) {
            return "problemas generados al Procesar la planilla";
        }
    }
}
