package com.radiolatino.dao;

import com.radiolatino.model.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class EventoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emisoradb2");

    public List<Evento> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Evento> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Evento.class, id));
        } finally {
            em.close();
        }
    }

    public void guardar(Evento evento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (evento.getId() == null) {
                em.persist(evento); // Crear nuevo evento
            } else {
                em.merge(evento); // Actualizar evento existente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Evento evento = em.find(Evento.class, id);
            if (evento != null) {
                em.getTransaction().begin();
                em.remove(evento);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public List<Evento> buscarPorCriterio(String criterio) {
        EntityManager em = emf.createEntityManager();
        try {
            // Usar una consulta JPQL para buscar eventos según el criterio
            return em.createQuery(
                            "SELECT e FROM Evento e WHERE LOWER(e.nombre) LIKE LOWER(:criterio) " +
                                    "OR LOWER(e.lugar) LIKE LOWER(:criterio) " +
                                    "OR LOWER(e.organizador) LIKE LOWER(:criterio)", Evento.class)
                    .setParameter("criterio", "%" + criterio + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
