package com.radiolatino.dao;

import com.radiolatino.model.Genero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class GeneroDAO {

    private EntityManagerFactory emf;

    public GeneroDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Genero> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Genero buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public List<Genero> buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT g FROM Genero g WHERE LOWER(g.nombre) LIKE :nombre",
                            Genero.class)
                    .setParameter("nombre", "%" + nombre.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void guardar(Genero genero) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (genero.getId() == null) {
                em.persist(genero);
            } else {
                em.merge(genero);
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
