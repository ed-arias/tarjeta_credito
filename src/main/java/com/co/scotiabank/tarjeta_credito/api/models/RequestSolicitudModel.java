package com.co.scotiabank.tarjeta_credito.api.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RequestSolicitudModel {

    private String tipoDocumento;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String correoElectronico;
    
}