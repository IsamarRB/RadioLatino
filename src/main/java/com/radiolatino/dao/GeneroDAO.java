package com.radiolatino.dao;

import com.radiolatino.model.Genero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class GeneroDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emisoradb2");

    public List<Genero> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Genero> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Genero.class, id));
        } finally {
            em.close();
        }
    }

    public void guardar(Genero genero) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (genero.getId() == null) {
                em.persist(genero); // Crear nuevo género
            } else {
                em.merge(genero); // Actualizar género existente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Genero genero = em.find(Genero.class, id);
            if (genero != null) {
                em.getTransaction().begin();
                em.remove(genero);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
