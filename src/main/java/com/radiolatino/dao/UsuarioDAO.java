package com.radiolatino.dao;

import com.radiolatino.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UsuarioDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EvaluacionPU");

    public void guardarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findByCorreo(String correo) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery(
                            "SELECT u FROM Usuario u WHERE LOWER(u.correo) = :correo", Usuario.class)
                    .setParameter("correo", correo.toLowerCase())
                    .getResultList();

            return usuarios.isEmpty() ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarUsuario(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
