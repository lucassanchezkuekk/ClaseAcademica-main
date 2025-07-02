package com.eduTech.unitarias1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduTech.unitarias1.model.G_Envio;

@Repository
public interface G_EnvioRepository extends JpaRepository<G_Envio, Integer> {
    
}

