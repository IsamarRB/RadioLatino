package com.radiolatino.service;

import com.radiolatino.model.Cancion;
import com.radiolatino.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService implements BaseService<Cancion> {

    private final CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public CancionService() {

    }

    @Override
    public List<Cancion> listarTodos() {
        return cancionRepository.findAll();
    }

    @Override
    public Optional<Cancion> buscarPorId(Long id) {
        return cancionRepository.findById(id);
    }

    @Override
    public Cancion guardar(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    @Override
    public void eliminar(Long id) {
        cancionRepository.deleteById(id);
    }
}
