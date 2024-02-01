package com.latam.pgd.procesadorplanillas.controladores;

import com.latam.pgd.procesadorplanillas.inyectores.impl.InyectorEmpleadosDummyImpl;
import com.latam.pgd.procesadorplanillas.inyectores.impl.InyectorEmpleadosRestImpl;
import com.latam.pgd.procesadorplanillas.inyectores.InyectorProveedorMiembros;
import com.latam.pgd.procesadorplanillas.servicios.ProcesadorPlanillas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPlanillas {

    ProcesadorPlanillas svrEmpleados = null;

    @Operation(
            summary = "verifica que la API este operando",
            description = "permite revisar que la api desplego correctamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation exitosa")
    })
    @GetMapping("/health")
    public String healthCheck(){
        return "la aplicacion se ejecuta correctamente";
    }

    @Operation(
            summary = "procesa planilla con datos Aleatorios",
            description = "genera los datos de 8 empleados y suma el valor total a pagar de la planilla")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation exitosa")
    })
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

    @Operation(
            summary = "integrador rest the empleados",
            description = "se usara para conectar con el proveedor de empleados Real")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation exitosa")
    })
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
