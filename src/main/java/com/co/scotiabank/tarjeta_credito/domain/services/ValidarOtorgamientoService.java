package com.co.scotiabank.tarjeta_credito.domain.services;

import java.util.Random;

import com.co.scotiabank.tarjeta_credito.api.models.ReqValidarOtorgamiento;
import com.co.scotiabank.tarjeta_credito.api.models.ResValidarOtorgamiento;
import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.repositories.ClienteRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValidarOtorgamientoService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public ResValidarOtorgamiento validarOtorgamientoAutomatico(ReqValidarOtorgamiento reqValidarOtorgamiento) throws Exception {

        Solicitud solicitud = solicitudRepository.findById(reqValidarOtorgamiento.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqValidarOtorgamiento.getIdSolicitud()));

        Cliente cliente = solicitud.getCliente();
        ResValidarOtorgamiento resValidarOtorgamiento = new ResValidarOtorgamiento();
        
        cliente.setResultadoOtorgamientoAutomatico(evaluarRiesgo());

        clienteRepository.save(cliente);
        solicitudRepository.save(solicitud);

        resValidarOtorgamiento.setResultado(cliente.getResultadoOtorgamientoAutomatico());
        resValidarOtorgamiento.setCupo(5825000L);
        resValidarOtorgamiento.setFranquicia("VISA");

        return resValidarOtorgamiento;
    }

    private String evaluarRiesgo() {
        Random random = new Random();

        if(random.nextInt() % 2 == 0){
            return "S";
        }
        else{
            return "N";
        }
    }
    
}
