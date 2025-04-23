package com.radiolatino.repository;

import com.radiolatino.model.Podcast;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import java.util.List;

@Stateless
public class PodcastRepository {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    public List<Podcast> findByGeneroNombre(String nombreGenero) {
        return em.createQuery("SELECT p FROM Podcast p WHERE p.genero.nombre = :nombreGenero", Podcast.class)
                .setParameter("nombreGenero", nombreGenero)
                .getResultList();
    }

    public Podcast findById(Long id) {
        return em.find(Podcast.class, id);
    }

    public void save(Podcast podcast) {
        em.persist(podcast);
    }

    public void update(Podcast podcast) {
        em.merge(podcast);
    }

    public void delete(Long id) {
        Podcast podcast = findById(id);
        if (podcast != null) {
            em.remove(podcast);
        }
    }
}

