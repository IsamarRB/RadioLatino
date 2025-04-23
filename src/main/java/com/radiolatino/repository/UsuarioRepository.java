package com.radiolatino.repository;

import com.radiolatino.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class UsuarioRepository {

    @PersistenceContext(unitName = "radiolatinoPU")
    private EntityManager em;

    public Usuario findByCorreoAndPassword(String correo, String password) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.password = :password",
                Usuario.class
        );
        query.setParameter("correo", correo);
        query.setParameter("password", password);

        return query.getResultStream().findFirst().orElse(null);
    }

    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario find(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario update(Usuario usuario) {
        return em.merge(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }
}
