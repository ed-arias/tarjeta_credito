package com.co.scotiabank.tarjeta_credito.api.models;

import lombok.Data;

@Data
public class ReqListasRestrictivas {
    private Long idSolicitud;
    private String tipoDocumento;
    private String numeroDocumento;
}
