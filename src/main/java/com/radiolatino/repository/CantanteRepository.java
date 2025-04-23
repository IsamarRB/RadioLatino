package com.radiolatino.repository;

import com.radiolatino.model.Cantante;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.List;

@Stateless
public class CantanteRepository {

    @PersistenceContext(unitName = "radiolatinoPU")
    private EntityManager em;

    // Búsqueda exacta
    public Cantante findByNombre(String nombre) {
        TypedQuery<Cantante> query = em.createQuery(
                "SELECT c FROM Cantante c WHERE c.nombre = :nombre", Cantante.class);
        query.setParameter("nombre", nombre);
        List<Cantante> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Búsqueda parcial e insensible a mayúsculas
    public List<Cantante> findByNombreContainingIgnoreCase(String nombre) {
        TypedQuery<Cantante> query = em.createQuery(
                "SELECT c FROM Cantante c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", Cantante.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }
}


