package com.co.scotiabank.tarjeta_credito.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = { "correoElectronico" }),
        @UniqueConstraint(name = "unique_client", columnNames = { "numeroDocumento", "tipoDocumento" }) })
public class Cliente implements Serializable {

    private static final long serialVersionUID = -4051346248859268371L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCliente;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false)
    private String numeroDocumento;

    @Column(nullable = false)
    private String primerNombre;
    private String segundoNombre;

    @Column(nullable = false)
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String direccion;

    @Column(unique = true, nullable = false)
    private String correoElectronico;

    @OneToMany
    @JoinColumn(name = "id_cliente")
    private Set<TarjetaCredito> tarjetasCredito;
}