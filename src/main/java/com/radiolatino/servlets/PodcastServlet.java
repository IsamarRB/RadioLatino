package com.radiolatino.servlets;

import com.radiolatino.model.Podcast;
import com.radiolatino.service.PodcastService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/PodcastServlet")
public class PodcastServlet extends HttpServlet {

    private final PodcastService service = new PodcastService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no proporcionada.");
            return;
        }

        switch (accion) {
            case "listar":
                listarPodcasts(req, resp);
                break;
            case "detalle":
                mostrarDetallePodcast(req, resp);
                break;
            case "eliminar":
                eliminarPodcast(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no reconocida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no proporcionada.");
            return;
        }

        if ("guardar".equals(accion)) {
            guardarPodcast(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados para manejar las acciones

    private void listarPodcasts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Podcast> podcasts = service.listarTodos();
        req.setAttribute("podcasts", podcasts);
        req.getRequestDispatcher("/WEB-INF/views/ListarPodcasts.jsp").forward(req, resp);
    }

    private void mostrarDetallePodcast(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Podcast> podcast = service.buscarPorId(id);
            if (podcast.isPresent()) {
                req.setAttribute("podcast", podcast.get());
                req.getRequestDispatcher("/WEB-INF/views/DetallePodcast.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Podcast no encontrado.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para buscar el podcast.");
        }
    }

    private void guardarPodcast(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

            String titulo = req.getParameter("titulo");
            String descripcion = req.getParameter("descripcion");
            String url = req.getParameter("url");

            if (titulo == null || titulo.isEmpty() || descripcion == null || descripcion.isEmpty() || url == null || url.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos insuficientes para guardar el podcast.");
                return;
            }

            Podcast podcast = new Podcast();
            podcast.setId(id);
            podcast.setTitulo(titulo);
            podcast.setUrlAudio(url);


            service.guardar(podcast);
            resp.sendRedirect("PodcastServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para guardar el podcast.");
        }
    }

    private void eliminarPodcast(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            service.eliminar(id);
            resp.sendRedirect("PodcastServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para eliminar el podcast.");
        }
    }
}

