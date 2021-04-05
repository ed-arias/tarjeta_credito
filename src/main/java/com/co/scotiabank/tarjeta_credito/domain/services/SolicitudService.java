package com.co.scotiabank.tarjeta_credito.domain.services;

import java.time.LocalDateTime;

import com.co.scotiabank.tarjeta_credito.api.models.ReqActualizarEstado;
import com.co.scotiabank.tarjeta_credito.api.models.RequestSolicitudModel;
import com.co.scotiabank.tarjeta_credito.api.models.ResponseSolicitudModel;
import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.repositories.ClienteRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public ResponseSolicitudModel crearSolicitud(RequestSolicitudModel solicitudModel){

        Cliente cliente = new Cliente();
        Solicitud solicitud = new Solicitud();
        ResponseSolicitudModel responseSolicitudModel = new ResponseSolicitudModel();

        cliente.setCorreoElectronico(solicitudModel.getCorreoElectronico());
        cliente.setDireccion(solicitudModel.getDireccion());
        cliente.setFechaNacimiento(solicitudModel.getFechaNacimiento());
        cliente.setNumeroDocumento(solicitudModel.getNumeroDocumento());
        cliente.setPrimerApellido(solicitudModel.getPrimerApellido());
        cliente.setPrimerNombre(solicitudModel.getPrimerNombre());
        cliente.setSegundoApellido(solicitudModel.getSegundoApellido());
        cliente.setSegundoNombre(solicitudModel.getSegundoNombre());
        cliente.setTipoDocumento(solicitudModel.getTipoDocumento());

        cliente = clienteRepository.save(cliente);

        solicitud.setCliente(cliente);
        solicitud.setEstado(1L);
        solicitud.setFechaSolicitud(LocalDateTime.now());

        solicitud = solicitudRepository.save(solicitud);

        responseSolicitudModel.setIdSolicitud(solicitud.getIdSolicitud());
        responseSolicitudModel.setIdEstado(solicitud.getEstado());

        return responseSolicitudModel;
    }

    public void actualizarEstadoSolicitud(ReqActualizarEstado reqActualizarEstado) throws Exception{

        Solicitud solicitud = solicitudRepository.findById(reqActualizarEstado.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqActualizarEstado.getIdSolicitud()));

        solicitud.setEstado(reqActualizarEstado.getIdEstado());
        solicitud.setFechaActualizacionEstado(LocalDateTime.now());

        solicitudRepository.save(solicitud);
    }
    
}
