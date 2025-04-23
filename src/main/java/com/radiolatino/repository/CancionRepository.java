package com.radiolatino.repository;

import com.radiolatino.model.Cancion;
import java.util.List;
import java.util.Optional;

public interface CancionRepository {
    List<Cancion> findAll();
    Optional<Cancion> findById(Long id);
    Cancion save(Cancion cancion);
    void deleteById(Long id);
    List<Cancion> findByGeneroNombre(String nombreGenero);
}

