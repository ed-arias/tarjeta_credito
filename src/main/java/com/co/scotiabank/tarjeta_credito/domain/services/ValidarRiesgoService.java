package com.co.scotiabank.tarjeta_credito.domain.services;

import java.util.Random;

import com.co.scotiabank.tarjeta_credito.api.models.ReqListasRestrictivas;
import com.co.scotiabank.tarjeta_credito.api.models.ResValidarRiesgo;
import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.repositories.ClienteRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValidarRiesgoService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public ResValidarRiesgo validarRiesgo(ReqListasRestrictivas reqListasRestrictivas) throws Exception {

        Solicitud solicitud = solicitudRepository.findById(reqListasRestrictivas.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqListasRestrictivas.getIdSolicitud()));

        Cliente cliente = solicitud.getCliente();
        ResValidarRiesgo resValidarRiesgo = new ResValidarRiesgo();

        cliente.setResultadoRiesgo(evaluarRiesgo());

        clienteRepository.save(cliente);
        solicitudRepository.save(solicitud);

        resValidarRiesgo.setRiesgo(cliente.getResultadoRiesgo());

        return resValidarRiesgo;
    }
    
    private String evaluarRiesgo() {
        Random random = new Random();
        String opciones = "ABCDEF";

        return String.valueOf(opciones.charAt(random.nextInt(opciones.length())));
    }
}
