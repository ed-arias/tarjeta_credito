package com.co.scotiabank.tarjeta_credito.api.models;

import lombok.Data;

@Data
public class ResponseDisponibilidadModel {
    private int status;
    private String statusDescription;
    private String message;
}
