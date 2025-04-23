package com.radiolatino.repository;

import com.radiolatino.model.Cancion;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CancionRepository {

    @PersistenceContext(unitName = "radiolatinoPU")
    private EntityManager em;

    public List<Cancion> findByGeneroNombre(String nombreGenero) {
        return em.createQuery(
                        "SELECT c FROM Cancion c WHERE c.genero.nombre = :nombreGenero", Cancion.class)
                .setParameter("nombreGenero", nombreGenero)
                .getResultList();
    }

    public Cancion findById(Long id) {
        return em.find(Cancion.class, id);
    }

    public void save(Cancion cancion) {
        em.persist(cancion);
    }

    public void update(Cancion cancion) {
        em.merge(cancion);
    }

    public void delete(Cancion cancion) {
        em.remove(em.contains(cancion) ? cancion : em.merge(cancion));
    }
}
