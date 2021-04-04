package com.co.scotiabank.tarjeta_credito.api.controllers;

import com.co.scotiabank.tarjeta_credito.api.models.ReqActualizarEstado;
import com.co.scotiabank.tarjeta_credito.api.models.ReqGenerarTarjeta;
import com.co.scotiabank.tarjeta_credito.api.models.ReqListasRestrictivas;
import com.co.scotiabank.tarjeta_credito.api.models.ReqValidarOtorgamiento;
import com.co.scotiabank.tarjeta_credito.api.models.RequestSolicitudModel;
import com.co.scotiabank.tarjeta_credito.api.models.ResGenerarTarjeta;
import com.co.scotiabank.tarjeta_credito.api.models.ResListasRestrictivas;
import com.co.scotiabank.tarjeta_credito.api.models.ResValidarOtorgamiento;
import com.co.scotiabank.tarjeta_credito.api.models.ResValidarRiesgo;
import com.co.scotiabank.tarjeta_credito.api.models.ResponseDisponibilidadModel;
import com.co.scotiabank.tarjeta_credito.api.models.ResponseSolicitudModel;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.services.DroolExecutionService;
import com.co.scotiabank.tarjeta_credito.domain.services.GenerarTarjetaService;
import com.co.scotiabank.tarjeta_credito.domain.services.ListasRestrictivasService;
import com.co.scotiabank.tarjeta_credito.domain.services.SolicitudService;
import com.co.scotiabank.tarjeta_credito.domain.services.ValidarOtorgamientoService;
import com.co.scotiabank.tarjeta_credito.domain.services.ValidarRiesgoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class TarjetaCreditoController {

    private final SolicitudService solicitudService;
    private final ListasRestrictivasService listasRestrictivasService;
    private final ValidarRiesgoService validarRiesgoService;
    private final ValidarOtorgamientoService validarOtorgamientoService;
    private final GenerarTarjetaService generarTarjetaService;
    private final DroolExecutionService droolsService;

    @PostMapping("/crearSolicitud")
    public ResponseEntity<ResponseSolicitudModel> crearSolicitud(@RequestBody RequestSolicitudModel solicitudModel) {
        return new ResponseEntity<>(solicitudService.crearSolicitud(solicitudModel), HttpStatus.OK);
    }

    @PostMapping("/listasRestrictivas")
    public ResponseEntity<ResListasRestrictivas> consultarListasRestrictivas(
            @RequestBody ReqListasRestrictivas reqListasRestrictivas) throws Exception {

        return new ResponseEntity<>(listasRestrictivasService.consultarListasRestrictivas(reqListasRestrictivas), HttpStatus.OK);
    }

    @PostMapping("/actualizarEstado")
    public void actualizarEstado(@RequestBody ReqActualizarEstado reqActualizarEstado) throws Exception{
        solicitudService.actualizarEstadoSolicitud(reqActualizarEstado);
    }

    @PostMapping("/validarRiesgo")
    public ResponseEntity<ResValidarRiesgo> validarRiesgo(@RequestBody ReqListasRestrictivas reqListasRestrictivas) throws Exception {
        return new ResponseEntity<>(validarRiesgoService.validarRiesgo(reqListasRestrictivas), HttpStatus.OK);
    }

    @PostMapping("/validarOtorgamientoAutomatico")
    public ResponseEntity<ResValidarOtorgamiento> validarOtorgamientoAutomatico(@RequestBody ReqValidarOtorgamiento validarOtorgamiento) throws Exception {
        return new ResponseEntity<>(validarOtorgamientoService.validarOtorgamientoAutomatico(validarOtorgamiento), HttpStatus.OK);
    }

    @PostMapping("/generarTarjeta")
    public ResponseEntity<ResGenerarTarjeta> generarTarjeta(@RequestBody ReqGenerarTarjeta reqGenerarTarjeta) throws Exception {
        return new ResponseEntity<>(generarTarjetaService.generarTarjeta(reqGenerarTarjeta), HttpStatus.OK);
    }

    @PostMapping("/motorReglas")
    public ResponseEntity<Solicitud> ejecutarMotor(@RequestBody ReqGenerarTarjeta reqGenerarTarjeta) throws Exception {
        return new ResponseEntity<>(droolsService.llamarMotorReglas(reqGenerarTarjeta), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDisponibilidadModel> mostrarRespuestaTest() {
        ResponseDisponibilidadModel disponibilidadModel = new ResponseDisponibilidadModel();
        disponibilidadModel.setMessage("Test de disponibilidad exitoso");
        disponibilidadModel.setStatus(HttpStatus.OK.value());
        disponibilidadModel.setStatusDescription(HttpStatus.OK.name());
        return new ResponseEntity<>(disponibilidadModel, HttpStatus.OK);
    }
}
