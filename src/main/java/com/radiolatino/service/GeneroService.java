package com.radiolatino.service;

import com.radiolatino.model.Genero;
import com.radiolatino.repository.GeneroRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class GeneroService implements BaseService<Genero> {

    @Inject
    private GeneroRepository generoRepository;

    @Override
    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    @Override
    public Optional<Genero> buscarPorId(Long id) {
        return generoRepository.findById(id);
    }

    @Override
    public Genero guardar(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }
}

