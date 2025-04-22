package com.radiolatino.service;

import com.radiolatino.model.Cancion;
import com.radiolatino.repository.CancionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CancionService {

    private final CancionRepository cancionRepository;

    public CancionService() {
        this.cancionRepository = new CancionRepository() {
            @Override
            public List<Cancion> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Cancion> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Cancion> S save(S entity) {
                return null;
            }

            @Override
            public <S extends Cancion> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public Optional<Cancion> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public List<Cancion> findAll() {
                return List.of();
            }

            @Override
            public List<Cancion> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Cancion entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Cancion> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Cancion> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Cancion> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<Cancion> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Cancion getOne(Long aLong) {
                return null;
            }

            @Override
            public Cancion getById(Long aLong) {
                return null;
            }

            @Override
            public Cancion getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Cancion> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Cancion> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Cancion> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Cancion> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Cancion> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Cancion> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Cancion, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public List<Cancion> findByGeneroNombre(String nombreGenero) {
                return List.of();
            }
        }; // Se instancia directamente (sin inyección de Spring)
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

