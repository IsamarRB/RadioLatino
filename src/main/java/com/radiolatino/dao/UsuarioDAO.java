package com.radiolatino.dao;

import com.radiolatino.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UsuarioDAO {

    private final EntityManagerFactory emf;

    public UsuarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Usuario findByCorreo(String correo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo", correo)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Retorna null si el usuario no se encuentra
        } finally {
            em.close();
        }
    }
}

