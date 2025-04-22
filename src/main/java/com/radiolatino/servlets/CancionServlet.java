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
        if (accion == null) accion = "listar";

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
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");
        if ("guardar".equals(accion)) {
            guardarCancion(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados

    private void listarCanciones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cancion> canciones = cancionService.listarTodos();
        req.setAttribute("canciones", canciones);
        req.getRequestDispatcher("/WEB-INF/vistas/listarCanciones.jsp").forward(req, resp);
    }

    private void mostrarDetalle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Cancion> cancion = cancionService.buscarPorId(id);
        if (cancion.isPresent()) {
            req.setAttribute("cancion", cancion.get());
            req.getRequestDispatcher("/WEB-INF/vistas/detalleCancion.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Canción no encontrada.");
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Optional<Cancion> cancion = cancionService.buscarPorId(id);
            cancion.ifPresent(c -> req.setAttribute("cancion", c));
        }
        req.getRequestDispatcher("/WEB-INF/vistas/formularioCancion.jsp").forward(req, resp);
    }

    private void guardarCancion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

        String titulo = req.getParameter("titulo");
        String genero = req.getParameter("genero");
        String cantante = req.getParameter("cantante");

        Cancion cancion = new Cancion();
        cancion.setId(id);
        cancion.setTitulo(titulo);
        cancion.setGenero(genero);
        cancion.setCantante(cantante);

        cancionService.guardar(cancion);
        resp.sendRedirect("CancionServlet?accion=listar");
    }

    private void eliminarCancion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        cancionService.eliminar(id);
        resp.sendRedirect("CancionServlet?accion=listar");
    }
}


