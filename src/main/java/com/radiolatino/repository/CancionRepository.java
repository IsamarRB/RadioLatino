package com.radiolatino.repository;

import com.radiolatino.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByGeneroNombre(String nombreGenero);
}
