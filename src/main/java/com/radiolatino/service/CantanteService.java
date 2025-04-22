package com.radiolatino.service;

import com.radiolatino.model.Cantante;
import com.radiolatino.repository.CantanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CantanteService implements BaseService<Cantante> {

    private CantanteRepository cantanteRepository;

    public CantanteService() {
        this.cantanteRepository = cantanteRepository;
    }

    @Override
    public List<Cantante> listarTodos() {
        return cantanteRepository.findAll();
    }

    @Override
    public Optional<Cantante> buscarPorId(Long id) {
        return cantanteRepository.findById(id);
    }

    @Override
    public Cantante guardar(Cantante cantante) {
        return cantanteRepository.save(cantante);
    }

    @Override
    public void eliminar(Long id) {
        cantanteRepository.deleteById(id);
    }

    // Búsqueda exacta por nombre
    public Cantante buscarPorNombreExacto(String nombre) {
        return cantanteRepository.findByNombre(nombre);
    }

    // Búsqueda parcial e insensible a mayúsculas
    public List<Cantante> buscarPorNombre(String nombre) {
        return cantanteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
