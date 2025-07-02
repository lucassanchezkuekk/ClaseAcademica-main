package com.eduTech.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduTech.unitarias1.model.ClaseAcademica;
import com.eduTech.unitarias1.service.ClaseAcademicaService;

@RestController
@RequestMapping("/api/clases")
public class ClaseAcademicaController {

    private final ClaseAcademicaService claseService;

    public ClaseAcademicaController(ClaseAcademicaService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    public List<ClaseAcademica> listarClases() {
        return claseService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseAcademica> obtenerPorId(@PathVariable int id) {
        return claseService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Eliminado endpoint duplicado para mantener solo /api/clases/{id}
    @PostMapping
    public ClaseAcademica crearClase(@RequestBody ClaseAcademica clase) {
        return claseService.guardar(clase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseAcademica> actualizar(@PathVariable int id, @RequestBody ClaseAcademica clase) {
        ClaseAcademica actualizada = claseService.actualizarClase(id, clase);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        claseService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}