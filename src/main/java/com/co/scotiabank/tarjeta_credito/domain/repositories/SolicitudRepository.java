package com.co.scotiabank.tarjeta_credito.domain.repositories;

import com.co.scotiabank.tarjeta_credito.domain.entities.Solicitud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{
    
}
