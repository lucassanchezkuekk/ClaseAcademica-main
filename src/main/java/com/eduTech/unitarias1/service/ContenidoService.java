package com.eduTech.unitarias1.service;

import org.springframework.stereotype.Service;

import com.eduTech.unitarias1.model.Contenido;
import com.eduTech.unitarias1.repository.ContenidoRepository;

import java.util.List;

@Service
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoService(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    public List<Contenido> obtenerTodos() {
        return contenidoRepository.findAll();
    }
}
