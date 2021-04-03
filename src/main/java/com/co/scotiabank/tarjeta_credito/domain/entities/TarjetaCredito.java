package com.co.scotiabank.tarjeta_credito.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarjeta_credito", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "numero" }, name = "unique_numero_entidadEmisora") })
public class TarjetaCredito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tarjeta;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private String franquicia;

    private Integer ccv;
    private LocalDate fechaExpriracion;

}