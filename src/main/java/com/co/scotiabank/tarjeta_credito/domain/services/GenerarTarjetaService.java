package com.co.scotiabank.tarjeta_credito.domain.services;

import java.time.LocalDate;

import com.co.scotiabank.tarjeta_credito.api.models.ReqGenerarTarjeta;
import com.co.scotiabank.tarjeta_credito.api.models.ResGenerarTarjeta;
import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;
import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;
import com.co.scotiabank.tarjeta_credito.domain.entities.TarjetaCredito;
import com.co.scotiabank.tarjeta_credito.domain.repositories.ClienteRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.SolicitudRepository;
import com.co.scotiabank.tarjeta_credito.domain.repositories.TarjetaCreditoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenerarTarjetaService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public ResGenerarTarjeta generarTarjeta(ReqGenerarTarjeta reqGenerarTarjeta) throws Exception{

        Solicitud solicitud = solicitudRepository.findById(reqGenerarTarjeta.getIdSolicitud()).orElseThrow(
                () -> new Exception("Solicitud no encontrada con id: " + reqGenerarTarjeta.getIdSolicitud()));

        Cliente cliente = solicitud.getCliente();
        TarjetaCredito tarjetaCredito = new TarjetaCredito();
        ResGenerarTarjeta resGenerarTarjeta = new ResGenerarTarjeta();

        tarjetaCredito.setCcv(1234);
        tarjetaCredito.setFechaExpriracion(LocalDate.now());
        tarjetaCredito.setFranquicia("VISA");
        tarjetaCredito.setNumero("9876-5432-1285-7419");

        cliente.getTarjetasCredito().add(tarjetaCredito);

        tarjetaCreditoRepository.save(tarjetaCredito);
        clienteRepository.save(cliente);
        solicitudRepository.save(solicitud);

        resGenerarTarjeta.setFechaVencimiento(tarjetaCredito.getFechaExpriracion());
        resGenerarTarjeta.setNumeroTarjeta(tarjetaCredito.getNumero());

        return resGenerarTarjeta;
    }
    
}
