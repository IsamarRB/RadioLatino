package com.radiolatino.repository;

import com.radiolatino.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CantanteRepository extends JpaRepository<Cantante, Long> {
    // Búsqueda exacta
    Cantante findByNombre(String nombre);

    // Búsqueda parcial e insensible a mayúsculas
    List<Cantante> findByNombreContainingIgnoreCase(String nombre);
}

