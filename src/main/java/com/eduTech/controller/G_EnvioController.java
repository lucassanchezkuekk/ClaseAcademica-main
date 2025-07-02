package com.eduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduTech.unitarias1.model.Estado;
import com.eduTech.unitarias1.model.G_Envio;
import com.eduTech.unitarias1.service.G_EnvioService;

@RestController
@RequestMapping("/api/envios")
public class G_EnvioController {
    
    @Autowired
    private G_EnvioService envioService;

    // Crear nuevo envío
    @PostMapping
    public ResponseEntity<G_Envio> crearEnvio(@RequestBody G_Envio envio) {
        G_Envio nuevoEnvio = envioService.crearEnvio(envio);
        return ResponseEntity.ok(nuevoEnvio);
    }

    // Obtener todos los envíos
    @GetMapping
    public ResponseEntity<List<G_Envio>> pedirEnvios() {
        List<G_Envio> envios = envioService.pedirEnvios();
        return ResponseEntity.ok(envios);
    }

    // Obtener envío por id
    @GetMapping("/{id}")
    public ResponseEntity<G_Envio> obtenerEnvioPorId(@PathVariable int id) {
        return envioService.obtenerEnvioId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizando estado de envio
    @PutMapping("/{id}/estado")
    public ResponseEntity<G_Envio> actualizarEstadoEnvio(
            @PathVariable int id,
            @RequestParam Estado estado) {
        G_Envio envioActualizado = envioService.actualizarEstadoEnvio(id, estado); //Pendiente, En Camino, 
                                                                                    //Entregado, Cancelado
        if (envioActualizado != null) {
            return ResponseEntity.ok(envioActualizado);
        }
        return ResponseEntity.notFound().build();
    }


}
