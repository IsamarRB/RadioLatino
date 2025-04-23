package com.radiolatino.repository;

import com.radiolatino.model.Cantante;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class CantanteRepository {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    public List<Cantante> findAll() {
        return em.createQuery("SELECT c FROM Cantante c", Cantante.class).getResultList();
    }

    public Optional<Cantante> findById(Long id) {
        Cantante cantante = em.find(Cantante.class, id);
        return Optional.ofNullable(cantante);
    }

    public Cantante save(Cantante cantante) {
        if (cantante.getId() == null) {
            em.persist(cantante);
            return cantante;
        } else {
            return em.merge(cantante);
        }
    }

    public void deleteById(Long id) {
        Cantante cantante = em.find(Cantante.class, id);
        if (cantante != null) {
            em.remove(cantante);
        }
    }

    public Cantante findByNombreExacto(String nombre) {
        return em.createQuery("SELECT c FROM Cantante c WHERE c.nombre = :nombre", Cantante.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Cantante> buscarPorNombre(String nombre) {
        return em.createQuery("SELECT c FROM Cantante c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", Cantante.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
    }
}
