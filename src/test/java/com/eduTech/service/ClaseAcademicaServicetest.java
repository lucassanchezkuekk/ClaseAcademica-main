package com.eduTech.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.eduTech.unitarias1.model.ClaseAcademica;
import com.eduTech.unitarias1.repository.ClaseAcademicaRepository;
import com.eduTech.unitarias1.service.ClaseAcademicaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClaseAcademicaServicetest {

    @Mock
    private ClaseAcademicaRepository claseRepository;

    @InjectMocks
    private ClaseAcademicaService claseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        ClaseAcademica clase = new ClaseAcademica(1, "Historia", "Edad Media");
        when(claseRepository.save(clase)).thenReturn(clase);

        ClaseAcademica resultado = claseService.guardar(clase);

        assertNotNull(resultado);
        assertEquals("Historia", resultado.getNombre());
        verify(claseRepository).save(clase);
    }

    @Test
    public void testObtenerTodas() {
        ClaseAcademica c1 = new ClaseAcademica(1, "Mate", "Algebra");
        ClaseAcademica c2 = new ClaseAcademica(2, "Lenguaje", "Ortografia");

        when(claseRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<ClaseAcademica> resultado = claseService.obtenerTodas();

        assertEquals(2, resultado.size());
        verify(claseRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPorId() {
        ClaseAcademica clase = new ClaseAcademica(1, "Ciencias", "Fisica basica");
        when(claseRepository.findById(1)).thenReturn(Optional.of(clase));

        Optional<ClaseAcademica> resultado = claseService.obtenerPorId(1);

        assertTrue(resultado.isPresent());

        assertEquals("Ciencias", resultado.get().getNombre());
        verify(claseRepository).findById(1);
    }

    @Test
    public void testActualizarClaseNoEncontrada() {
        ClaseAcademica actualizada = new ClaseAcademica(99, "No existe", "Sin horario");
        when(claseRepository.findById(99)).thenReturn(Optional.empty());

        ClaseAcademica resultado = claseService.actualizarClase(99, actualizada);

        assertNull(resultado);
        verify(claseRepository).findById(99);
        verify(claseRepository, never()).save(any(ClaseAcademica.class));
    }

    @Test
    public void testEliminarPorId() {
        doNothing().when(claseRepository).deleteById(1);
        claseService.eliminarPorId(1);
        verify(claseRepository).deleteById(1);
    }
}