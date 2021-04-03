package com.co.scotiabank.tarjeta_credito.domain.repositories;

import com.co.scotiabank.tarjeta_credito.domain.entities.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
