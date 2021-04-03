package com.co.scotiabank.tarjeta_credito.api.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResGenerarTarjeta {
    
    private String numeroTarjeta;
    private LocalDate fechaVencimiento;
}
