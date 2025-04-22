package com.radiolatino.service;

import com.radiolatino.model.Cancion;
import com.radiolatino.repository.CancionRepository;

import java.util.List;
import java.util.Optional;

public class CancionService {

    private final CancionRepository cancionRepository;

    public CancionService() {
        this.cancionRepository = new CancionRepository(); // Se instancia directamente (sin inyección de Spring)
    }

    public List<Cancion> listarTodos() {
        return cancionRepository.findAll();
    }

    public Optional<Cancion> buscarPorId(Long id) {
        return cancionRepository.findById(id);
    }

    public Cancion guardar(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public void eliminar(Long id) {
        cancionRepository.deleteById(id);
    }
}

