package com.co.scotiabank.tarjeta_credito.api.models;

import lombok.Data;

@Data
public class ReqActualizarEstado {

    private Long idSolicitud;
    private Long idEstado;

}
