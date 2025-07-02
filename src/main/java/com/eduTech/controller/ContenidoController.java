package com.eduTech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduTech.unitarias1.model.Contenido;
import com.eduTech.unitarias1.service.ContenidoService;

import java.util.List;

@RestController
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @GetMapping("/contenido")
    public List<Contenido> obtenerContenido() {
        return contenidoService.obtenerTodos();
    }
}
