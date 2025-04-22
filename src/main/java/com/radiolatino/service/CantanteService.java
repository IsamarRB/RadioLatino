package com.radiolatino.service;

import com.radiolatino.model.Cantante;
import com.radiolatino.repository.CantanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CantanteService implements BaseService<Cantante> {

    private final CantanteRepository cantanteRepository;

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
}
