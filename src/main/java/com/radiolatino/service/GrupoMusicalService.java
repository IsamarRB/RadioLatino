package com.radiolatino.service;

import com.radiolatino.dao.GrupoMusicalDAO;
import com.radiolatino.model.GrupoMusical;

import java.util.List;
import java.util.Optional;

public class GrupoMusicalService {
    private final GrupoMusicalDAO grupoMusicalDAO = new GrupoMusicalDAO();

    public List<GrupoMusical> listarTodos() {
        return grupoMusicalDAO.listarTodos();
    }

    public Optional<GrupoMusical> buscarPorId(Long id) {
        return Optional.ofNullable(grupoMusicalDAO.buscarPorId(id));
    }

    public void guardar(GrupoMusical grupo) {
        grupoMusicalDAO.guardar(grupo);
    }

    public void eliminar(Long id) {
        grupoMusicalDAO.eliminar(id);
    }
}