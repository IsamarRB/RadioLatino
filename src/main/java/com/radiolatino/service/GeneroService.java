package com.radiolatino.service;

import com.radiolatino.model.Genero;
import com.radiolatino.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService implements BaseService<Genero> {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

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
