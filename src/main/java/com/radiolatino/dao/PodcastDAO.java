package com.radiolatino.dao;

import com.radiolatino.model.Podcast;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class PodcastDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emisoradb2");

    public List<Podcast> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Podcast p", Podcast.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Podcast> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Podcast.class, id));
        } finally {
            em.close();
        }
    }

    public void guardar(Podcast podcast) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (podcast.getId() == null) {
                em.persist(podcast); // Crear un nuevo podcast
            } else {
                em.merge(podcast); // Actualizar un podcast existente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Podcast podcast = em.find(Podcast.class, id);
            if (podcast != null) {
                em.getTransaction().begin();
                em.remove(podcast);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}


