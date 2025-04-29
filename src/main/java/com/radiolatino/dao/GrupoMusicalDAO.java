package com.radiolatino.dao;

import com.radiolatino.model.GrupoMusical;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class GrupoMusicalDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emisoradb2");

    public List<GrupoMusical> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT g FROM GrupoMusical g", GrupoMusical.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<GrupoMusical> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(GrupoMusical.class, id));
        } finally {
            em.close();
        }
    }

    public void guardar(GrupoMusical grupo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (grupo.getId() == null) {
                em.persist(grupo); // Crear nuevo grupo musical
            } else {
                em.merge(grupo); // Actualizar grupo musical existente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            GrupoMusical grupo = em.find(GrupoMusical.class, id);
            if (grupo != null) {
                em.getTransaction().begin();
                em.remove(grupo);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

}
