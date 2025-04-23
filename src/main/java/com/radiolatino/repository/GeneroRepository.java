package com.radiolatino.repository;

import com.radiolatino.model.Genero;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class GeneroRepository {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    public List<Genero> findAll() {
        return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
    }

    public Optional<Genero> findById(Long id) {
        return Optional.ofNullable(em.find(Genero.class, id));
    }

    public Genero save(Genero genero) {
        if (genero.getId() == null) {
            em.persist(genero);
            return genero;
        } else {
            return em.merge(genero);
        }
    }

    public void deleteById(Long id) {
        Genero genero = em.find(Genero.class, id);
        if (genero != null) {
            em.remove(genero);
        }
    }
}
