package com.radiolatino.service;

import com.radiolatino.model.Cantante;
import com.radiolatino.repository.CantanteRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class CantanteService implements BaseService<Cantante> {

    @Inject
    private CantanteRepository cantanteRepository;

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

    public Cantante buscarPorNombreExacto(String nombre) {
        return cantanteRepository.findByNombreExacto(nombre);
    }

    public List<Cantante> buscarPorNombre(String nombre) {
        return cantanteRepository.buscarPorNombre(nombre);
    }
}

