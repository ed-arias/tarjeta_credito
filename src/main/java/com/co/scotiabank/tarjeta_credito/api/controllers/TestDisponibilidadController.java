package com.co.scotiabank.tarjeta_credito.api.controllers;

import com.co.scotiabank.tarjeta_credito.api.models.ResponseDisponibilidadModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestDisponibilidadController {

    @GetMapping("/test")
    public ResponseEntity<ResponseDisponibilidadModel> mostrarRespuestaTest() {
        ResponseDisponibilidadModel disponibilidadModel = new ResponseDisponibilidadModel();
        disponibilidadModel.setMensaje("Test de disponibilidad exitoso");
        disponibilidadModel.setStatus(HttpStatus.OK.value());
        disponibilidadModel.setStatusDescription(HttpStatus.OK.name());
        return new ResponseEntity<>(disponibilidadModel, HttpStatus.OK);
    }
}
