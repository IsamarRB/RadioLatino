package com.radiolatino.repository;

import com.radiolatino.model.Genero;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless
public class GeneroRepository {

    @PersistenceContext(unitName = "RadiolatinoPU")
    private EntityManager em;

    public Genero findByNombre(String nombre) {
        try {
            return em.createQuery("SELECT g FROM Genero g WHERE g.nombre = :nombre", Genero.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // o lanzar una excepción personalizada si prefieres
        }
    }

    public void save(Genero genero) {
        em.persist(genero);
    }

    public Genero find(Long id) {
        return em.find(Genero.class, id);
    }

    public void update(Genero genero) {
        em.merge(genero);
    }

    public void delete(Long id) {
        Genero genero = em.find(Genero.class, id);
        if (genero != null) {
            em.remove(genero);
        }
    }
}
