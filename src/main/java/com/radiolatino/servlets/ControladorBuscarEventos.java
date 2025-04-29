package com.radiolatino.servlets;

import com.radiolatino.model.Evento;
import com.radiolatino.service.EventoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/BuscarEventos")
public class ControladorBuscarEventos extends HttpServlet {
    private final EventoService eventoService = new EventoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String criterio = req.getParameter("criterio");

        if (criterio == null || criterio.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Criterio de búsqueda no proporcionado.");
            return;
        }

        List<Evento> eventos = eventoService.buscarPorCriterio(criterio);
        req.setAttribute("eventos", eventos);
        req.getRequestDispatcher("/WEB-INF/vistas/listarEventos.jsp").forward(req, resp);
    }
}
