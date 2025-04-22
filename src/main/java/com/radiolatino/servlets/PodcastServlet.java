package com.radiolatino.servlets;


import com.radiolatino.model.Podcast;
import com.radiolatino.service.PodcastService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/PodcastServlet")
public class PodcastServlet extends HttpServlet {
    private final PodcastService service = new PodcastService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("listar".equals(accion)) {
            List<Podcast> podcasts = service.listarTodos();
            req.setAttribute("podcasts", podcasts);
            req.getRequestDispatcher("/WEB-INF/vistas/listarPodcasts.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crear, editar, eliminar
    }
}
