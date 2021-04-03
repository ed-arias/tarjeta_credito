package com.co.scotiabank.tarjeta_credito.domain.services;

import java.util.Random;

import com.co.scotiabank.tarjeta_credito.api.models.ReqListasRestrictivas;
import com.co.scotiabank.tarjeta_credito.api.models.ResListasRestrictivas;
import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.repositories.ClienteRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListasRestrictivasService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public ResListasRestrictivas consultarListasRestrictivas(ReqListasRestrictivas reqListasRestrictivas) throws Exception {

        Solicitud solicitud = solicitudRepository.findById(reqListasRestrictivas.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqListasRestrictivas.getIdSolicitud()));

        Cliente cliente = solicitud.getCliente();
        ResListasRestrictivas resListasRestrictivas = new ResListasRestrictivas();

        cliente.setResultadoListasRestrictivas(evaluarListasRestrictivas());

        clienteRepository.save(cliente);
        solicitudRepository.save(solicitud);

        resListasRestrictivas.setEncontrado(cliente.getResultadoListasRestrictivas());

        return resListasRestrictivas;
    }

    private String evaluarListasRestrictivas() {
        Random random = new Random();
        String opciones = "SN";

        return String.valueOf(opciones.charAt(random.nextInt(opciones.length())));
    }

}
