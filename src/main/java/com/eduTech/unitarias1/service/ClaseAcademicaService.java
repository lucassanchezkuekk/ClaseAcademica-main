package com.eduTech.unitarias1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eduTech.unitarias1.model.ClaseAcademica;
import com.eduTech.unitarias1.repository.ClaseAcademicaRepository;

@Service
public class ClaseAcademicaService {

    private final ClaseAcademicaRepository claseRepository;

    public ClaseAcademicaService(ClaseAcademicaRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public List<ClaseAcademica> obtenerTodas() {
        return claseRepository.findAll();
    }

    public ClaseAcademica guardar(ClaseAcademica clase) {
        return claseRepository.save(clase);
    }

    public Optional<ClaseAcademica> obtenerPorId(int id) {
        return claseRepository.findById(id);
    }

    public void eliminarPorId(int id) {
        claseRepository.deleteById(id);
    }

    public ClaseAcademica actualizarClase(int id, ClaseAcademica nuevaClase) {
        return claseRepository.findById(id).map(clase -> {
            clase.setNombre(nuevaClase.getNombre());
            clase.setDescripcion(nuevaClase.getDescripcion());
            return claseRepository.save(clase);
        }).orElse(null);
    }
}