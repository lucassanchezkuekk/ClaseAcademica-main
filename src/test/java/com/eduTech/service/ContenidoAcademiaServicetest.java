
package com.eduTech.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eduTech.unitarias1.model.Contenido;
import com.eduTech.unitarias1.repository.ContenidoRepository;
import com.eduTech.unitarias1.service.ContenidoService;

public class ContenidoAcademiaServicetest {
    private ContenidoRepository contenidoRepository;
    private ContenidoService contenidoService;

    @BeforeEach
    public void setUp(){
          // Simulamos el repositorio (no se conecta a BD real)
    
        contenidoRepository = mock(ContenidoRepository.class);
        contenidoService = new ContenidoService(contenidoRepository);
    }
    @Test
    public void TestObtenerTodos(){
         // Creamos datos falsos
        Contenido  c1 = new Contenido("Algebra", "Basico", "lucas");
        Contenido c2 = new Contenido("Historia", "Edad Media", "Ana");

        List<Contenido> contenidosSimulados = Arrays.asList(c1, c2);

        // Indicamos qué debe devolver el repo simulado

        when(contenidoRepository.findAll()).thenReturn(contenidosSimulados);

        // Llamamos al método del servicio

        List<Contenido> resultado = contenidoService.obtenerTodos();

        // Verificamos resultados esperados
        assertEquals(2, resultado.size());
        assertEquals("Algebra", resultado.get(0).getTitulo());
        assertEquals("lucas", resultado.get(0).getAutor());

          // Verificamos que el repo fue llamado 1 vez
        
        verify(contenidoRepository, times(1)).findAll();
    }
    
}


        