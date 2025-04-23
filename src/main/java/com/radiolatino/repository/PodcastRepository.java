package com.radiolatino.repository;

import com.radiolatino.model.Podcast;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class PodcastRepository {

    @PersistenceContext(unitName = "radiolatinoPU")
    private EntityManager em;

    public List<Podcast> findAll() {
        return em.createQuery("SELECT p FROM Podcast p", Podcast.class).getResultList();
    }

    public Optional<Podcast> findById(Long id) {
        Podcast podcast = em.find(Podcast.class, id);
        return Optional.ofNullable(podcast);
    }

    public Podcast save(Podcast podcast) {
        if (podcast.getId() == null) {
            em.persist(podcast);
            return podcast;
        } else {
            return em.merge(podcast);
        }
    }

    public void deleteById(Long id) {
        Podcast podcast = em.find(Podcast.class, id);
        if (podcast != null) {
            em.remove(podcast);
        }
    }
}
