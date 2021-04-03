package com.co.scotiabank.tarjeta_credito.api.models;

import lombok.Data;

@Data
public class ReqValidarOtorgamiento {
    
    private Long idSolicitud;
    private String tipoRiesgo;
    private Long cantidadIngresos;
}
