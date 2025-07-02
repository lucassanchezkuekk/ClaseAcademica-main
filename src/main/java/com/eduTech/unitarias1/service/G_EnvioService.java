package com.eduTech.unitarias1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduTech.unitarias1.model.Estado;
import com.eduTech.unitarias1.model.G_Envio;
import com.eduTech.unitarias1.repository.G_EnvioRepository;

@Service
public class G_EnvioService  {
    
    @Autowired
    private G_EnvioRepository envioRepository;

    // Crear un nuevo envío
    public G_Envio crearEnvio(G_Envio envio) {
        envio.setFechaEnvio(LocalDateTime.now());
        envio.setEstadoEnvio(Estado.PENDIENTE);
        return envioRepository.save(envio);
    }

    // Obtener todos los envíos
    public List<G_Envio> pedirEnvios() {
        return envioRepository.findAll();
    }

    // Obtener envío por ID
    public Optional<G_Envio> obtenerEnvioId(int id) {
        return envioRepository.findById(id);
    }

    // Actualizar estado de envío
    public G_Envio actualizarEstadoEnvio(int id, Estado nuevoEstado) {
        Optional<G_Envio> envioOptional = envioRepository.findById(id);
        if (envioOptional.isPresent()) {
            G_Envio envio = envioOptional.get();
            envio.setEstadoEnvio(nuevoEstado);
            return envioRepository.save(envio);
        }
        return null;
    }

    
}
