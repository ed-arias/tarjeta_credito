package com.co.scotiabank.tarjeta_credito.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class TestDisponibilidadController {
    
    @GetMapping("test")
    public ResponseEntity<String> mostrarRespuestaTest(){
        return new ResponseEntity<>("test ok", HttpStatus.OK);
    }
}
