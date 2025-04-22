package com.radiolatino.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> listarTodos();
    Optional<T> buscarPorId(Long id);
    T guardar(T entidad);
    void eliminar(Long id);
}