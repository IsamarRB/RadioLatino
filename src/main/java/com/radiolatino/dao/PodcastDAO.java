package com.radiolatino.dao;

import com.radiolatino.model.Podcast;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PodcastDAO {

    private EntityManagerFactory emf;

    public PodcastDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Podcast> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Podcast p", Podcast.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Podcast> buscarPorTituloOGenero(String criterio) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Podcast p WHERE LOWER(p.titulo) LIKE :criterio OR LOWER(p.genero) LIKE :criterio",
                            Podcast.class)
                    .setParameter("criterio", "%" + criterio.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void guardar(Podcast podcast) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (podcast.getId() == null) {
                em.persist(podcast);
            } else {
                em.merge(podcast);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Podcast buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Podcast.class, id);
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

