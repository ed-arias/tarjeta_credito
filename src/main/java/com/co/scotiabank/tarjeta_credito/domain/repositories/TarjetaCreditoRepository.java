package com.co.scotiabank.tarjeta_credito.domain.repositories;

import com.co.scotiabank.tarjeta_credito.domain.entities.TarjetaCredito;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, Long>{
    
}
