package com.radiolatino.dao;

import com.radiolatino.model.Cancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CancionDAO {

    private EntityManagerFactory emf;

    public CancionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Cancion> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cancion c", Cancion.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cancion> buscarPorTituloOGenero(String criterio) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Cancion c WHERE LOWER(c.titulo) LIKE :criterio OR LOWER(c.genero) LIKE :criterio",
                            Cancion.class)
                    .setParameter("criterio", "%" + criterio.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void guardar(Cancion cancion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (cancion.getId() == null) {
                em.persist(cancion); // Crear nueva canción
            } else {
                em.merge(cancion); // Actualizar canción existente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Cancion buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cancion.class, id);
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Cancion cancion = em.find(Cancion.class, id);
            if (cancion != null) {
                em.getTransaction().begin();
                em.remove(cancion);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
