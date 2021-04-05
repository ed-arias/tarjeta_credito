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
    private final DroolExecutionService droolExecutionService;

    public ResValidarOtorgamiento validarOtorgamientoAutomatico(ReqValidarOtorgamiento reqValidarOtorgamiento) throws Exception {

        Solicitud solicitud = solicitudRepository.findById(reqValidarOtorgamiento.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqValidarOtorgamiento.getIdSolicitud()));

        Cliente cliente = solicitud.getCliente();
        cliente.setIngresos(reqValidarOtorgamiento.getCantidadIngresos());
        ResValidarOtorgamiento resValidarOtorgamiento = new ResValidarOtorgamiento();

        solicitud = droolExecutionService.executeRules(solicitud);
        
        cliente.setResultadoOtorgamientoAutomatico(solicitud.getCliente().getResultadoOtorgamientoAutomatico());
        cliente.setIngresos(reqValidarOtorgamiento.getCantidadIngresos());

        clienteRepository.save(cliente);
        solicitudRepository.save(solicitud);

        resValidarOtorgamiento.setResultado(cliente.getResultadoOtorgamientoAutomatico());
        resValidarOtorgamiento.setCupo(solicitud.getCupoOtorgado());
        resValidarOtorgamiento.setFranquicia("VISA");

        return resValidarOtorgamiento;
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
    
}
