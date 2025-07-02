package com.eduTech.controller;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.eduTech.unitarias1.model.ClaseAcademica;
import com.eduTech.unitarias1.service.ClaseAcademicaService;

import org.springframework.test.context.ContextConfiguration;
import com.eduTech.unitarias1.GestionEnvioApplication;

@WebMvcTest(controllers = ClaseAcademicaController.class)
@ContextConfiguration(classes = GestionEnvioApplication.class)
public class ClaseAcademicaControllertest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClaseAcademicaService claseAcademicaService;

    @Test
    void testClasePorid() throws Exception {
        ClaseAcademica clase = new ClaseAcademica(1, "MATEMATICAS", "lunes 10:00");
        Mockito.when(claseAcademicaService.obtenerPorId(1)).thenReturn(Optional.of(clase));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("MATEMATICAS"))
                .andExpect(jsonPath("$.descripcion").value("lunes 10:00"));
    }

    @Test
    void testCrearClase() throws Exception {
        ClaseAcademica nuevaClase = new ClaseAcademica(3, "FISICA", "miércoles 14:00");
        Mockito.when(claseAcademicaService.guardar(Mockito.any(ClaseAcademica.class))).thenReturn(nuevaClase);
        String json = "{\"id\":3,\"nombre\":\"FISICA\",\"descripcion\":\"miércoles 14:00\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clases")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("FISICA"))
                .andExpect(jsonPath("$.descripcion").value("miércoles 14:00"));
    }

    @Test
    void testActualizarClase() throws Exception {
        ClaseAcademica actualizada = new ClaseAcademica(1, "MATEMATICAS", "lunes 12:00");
        Mockito.when(claseAcademicaService.actualizarClase(Mockito.eq(1), Mockito.any(ClaseAcademica.class))).thenReturn(actualizada);
        String json = "{\"id\":1,\"nombre\":\"MATEMATICAS\",\"descripcion\":\"lunes 12:00\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/clases/1")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("MATEMATICAS"))
                .andExpect(jsonPath("$.descripcion").value("lunes 12:00"));
    }

    @Test
    void testClasePorIdNoEncontrada() throws Exception {
        Mockito.when(claseAcademicaService.obtenerPorId(2)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clases/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListarClases() throws Exception {
        ClaseAcademica clase1 = new ClaseAcademica(1, "MATEMATICAS", "lunes 10:00");
        ClaseAcademica clase2 = new ClaseAcademica(2, "HISTORIA", "martes 12:00");
        Mockito.when(claseAcademicaService.obtenerTodas()).thenReturn(java.util.Arrays.asList(clase1, clase2));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("MATEMATICAS"))
                .andExpect(jsonPath("$[1].nombre").value("HISTORIA"));
    }
        @Test
    void testActualizarClaseNoEncontrada() throws Exception {
        Mockito.when(claseAcademicaService.actualizarClase(Mockito.eq(99), Mockito.any(ClaseAcademica.class))).thenReturn(null);
        String json = "{\"id\":99,\"nombre\":\"NOEXISTE\",\"descripcion\":\"sin horario\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/clases/99")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarClase() throws Exception {
        Mockito.doNothing().when(claseAcademicaService).eliminarPorId(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clases/1"))
                .andExpect(status().isNoContent());
    }
}
