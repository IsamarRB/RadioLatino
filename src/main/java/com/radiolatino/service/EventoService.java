package com.radiolatino.service;

import com.radiolatino.dao.EventoDAO;
import com.radiolatino.model.Evento;

import java.util.List;

public class EventoService {
    private final EventoDAO eventoDAO = new EventoDAO();

    public List<Evento> buscarPorCriterio(String criterio) {
        return eventoDAO.buscarPorCriterio(criterio);
    }
}
