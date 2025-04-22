package com.radiolatino.controller;


import com.radiolatino.dao.CancionDAO;
import com.radiolatino.dao.PodcastDAO;
import com.radiolatino.model.Cancion;
import com.radiolatino.model.Podcast;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/buscar")
public class BuscarEventosServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    public BuscarEventosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String criterio = request.getParameter("criterio");
        CancionDAO cancionDAO = new CancionDAO(emf);
        PodcastDAO podcastDAO = new PodcastDAO(emf);

        List<Cancion> canciones;
        List<Podcast> podcasts;

        if (criterio == null || criterio.trim().isEmpty()) {
            canciones = cancionDAO.findAll();
            podcasts = podcastDAO.findAll();
        } else {
            canciones = cancionDAO.buscarPorTituloOGenero(criterio);
            podcasts = podcastDAO.buscarPorTituloOGenero(criterio);
        }

        request.setAttribute("canciones", canciones);
        request.setAttribute("podcasts", podcasts);
        request.setAttribute("criterio", criterio);

        request.getRequestDispatcher("/WEB-INF/jsp/mostrarEventos.jsp").forward(request, response);
    }
}

