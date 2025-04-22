package com.radiolatino.dao;

import com.radiolatino.model.Cantante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CantanteDAO {

    private EntityManagerFactory emf;

    public CantanteDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Cantante> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cantante c", Cantante.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Cantante buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cantante.class, id);
        } finally {
            em.close();
        }
    }

    public List<Cantante> buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Cantante c WHERE LOWER(c.nombre) LIKE :nombre",
                            Cantante.class)
                    .setParameter("nombre", "%" + nombre.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void guardar(Cantante cantante) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (cantante.getId() == null) {
                em.persist(cantante);
            } else {
                em.merge(cantante);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Cantante cantante = em.find(Cantante.class, id);
            if (cantante != null) {
                em.getTransaction().begin();
                em.remove(cantante);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}

