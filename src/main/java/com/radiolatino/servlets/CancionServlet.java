package com.radiolatino.servlets;

import com.radiolatino.model.Cancion;
import com.radiolatino.service.CancionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/CancionServlet")
public class CancionServlet extends HttpServlet {

    private final CancionService cancionService = new CancionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no proporcionada.");
            return;
        }

        switch (accion) {
            case "listar":
                listarCanciones(req, resp);
                break;
            case "detalle":
                mostrarDetalle(req, resp);
                break;
            case "editar":
                mostrarFormularioEdicion(req, resp);
                break;
            case "eliminar":
                eliminarCancion(req, resp);
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
            guardarCancion(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados

    private void listarCanciones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cancion> canciones = cancionService.listarTodos();
        req.setAttribute("Canciones.jsp", canciones);
        req.getRequestDispatcher("/WEB-INF/ListarCanciones.jsp").forward(req, resp);
    }

    private void mostrarDetalle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Cancion> cancion = cancionService.buscarPorId(id);
            if (cancion.isPresent()) {
                req.setAttribute("cancion", cancion.get());
                req.getRequestDispatcher("/WEB-INF/DetalleCancion.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Canción no encontrada.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para buscar la canción.");
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                Optional<Cancion> cancion = cancionService.buscarPorId(id);
                if (cancion.isPresent()) {
                    req.setAttribute("cancion", cancion.get());
                }
            }
            req.getRequestDispatcher("/WEB-INF/FormularioCancion.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para editar la canción.");
        }
    }

    private void guardarCancion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

            String titulo = req.getParameter("titulo");
            String genero = req.getParameter("genero");
            String cantante = req.getParameter("cantante");

            if (titulo == null || genero == null || cantante == null || titulo.isEmpty() || genero.isEmpty() || cantante.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos insuficientes para guardar la canción.");
                return;
            }

            Cancion cancion = new Cancion();
            cancion.setId(id);
            cancion.setTitulo(titulo);
            cancion.setGenero(genero);
            cancion.setCantante(cantante);

            cancionService.guardar(cancion);
            resp.sendRedirect("CancionServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para guardar la canción.");
        }
    }

    private void eliminarCancion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            cancionService.eliminar(id);
            resp.sendRedirect("CancionServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para eliminar la canción.");
        }
    }
}
