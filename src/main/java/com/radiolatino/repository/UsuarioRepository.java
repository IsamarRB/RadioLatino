package com.radiolatino.repository;

import com.radiolatino.model.Usuario;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsuarioRepository {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(em.find(Usuario.class, id));
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            return em.merge(usuario);
        }
    }

    public void deleteById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public Usuario findByCorreo(String correo, String password) {
        try {
            return em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.password = :password", Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // No encontrado o error
        }
    }
}

