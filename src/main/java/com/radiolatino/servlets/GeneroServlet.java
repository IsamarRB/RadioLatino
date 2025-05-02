package com.radiolatino.servlets;

import com.radiolatino.model.Genero;
import com.radiolatino.service.GeneroService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/GeneroServlet")
public class GeneroServlet extends HttpServlet {

    private final GeneroService service = new GeneroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no proporcionada.");
            return;
        }

        switch (accion) {
            case "listar":
                listarGeneros(req, resp);
                break;
            case "detalle":
                mostrarDetalleGenero(req, resp);
                break;
            case "eliminar":
                eliminarGenero(req, resp);
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
            guardarGenero(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados para manejar las acciones

    private void listarGeneros(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genero> generos = service.listarTodos();
        req.setAttribute("generos", generos);
        req.getRequestDispatcher("/WEB-INF/views/ListarGenero.jsp").forward(req, resp);
    }

    private void mostrarDetalleGenero(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Genero> genero = service.buscarPorId(id);
            if (genero.isPresent()) {
                req.setAttribute("genero", genero.get());
                req.getRequestDispatcher("/WEB-INF/views/DetalleGenero.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Género no encontrado.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para buscar el género.");
        }
    }

    private void guardarGenero(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

            String nombre = req.getParameter("nombre");

            if (nombre == null || nombre.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos insuficientes para guardar el género.");
                return;
            }

            Genero genero = new Genero();
            genero.setId(id);
            genero.setNombre(nombre);

            service.guardar(genero);
            resp.sendRedirect("GeneroServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para guardar el género.");
        }
    }

    private void eliminarGenero(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            service.eliminar(id);
            resp.sendRedirect("GeneroServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para eliminar el género.");
        }
    }
}


