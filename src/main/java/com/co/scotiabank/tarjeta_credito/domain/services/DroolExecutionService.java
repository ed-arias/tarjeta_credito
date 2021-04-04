package com.co.scotiabank.tarjeta_credito.domain.services;

import com.co.scotiabank.tarjeta_credito.api.models.ReqGenerarTarjeta;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DroolExecutionService {

    private final KieSession kieSession;
    private final SolicitudRepository solicitudRepository;

    public Solicitud llamarMotorReglas(ReqGenerarTarjeta reqGenerarTarjeta) throws Exception{
        
        Solicitud solicitud = solicitudRepository.findById(reqGenerarTarjeta.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqGenerarTarjeta.getIdSolicitud()));

        return executeRules(solicitud);
    }

    public Solicitud executeRules(Solicitud solicitud) throws Exception {

        kieSession.insert(solicitud);
        kieSession.fireAllRules();
        kieSession.dispose();

        return solicitud;
    }
}
