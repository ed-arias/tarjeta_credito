package com.co.scotiabank.tarjeta_credito.api.models;

import lombok.Data;

@Data
public class ResValidarOtorgamiento {
    
    private String resultado;
	private String franquicia;
    private Long cupo;
}