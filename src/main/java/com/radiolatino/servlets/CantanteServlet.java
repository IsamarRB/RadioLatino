package com.radiolatino.servlets;

import com.radiolatino.model.Cantante;
import com.radiolatino.service.CantanteService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/CantanteServlet")
public class CantanteServlet extends HttpServlet {

    private final CantanteService service = new CantanteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no proporcionada.");
            return;
        }

        switch (accion) {
            case "listar":
                listarCantantes(req, resp);
                break;
            case "detalle":
                mostrarDetalleCantante(req, resp);
                break;
            case "eliminar":
                eliminarCantante(req, resp);
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
            guardarCantante(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados para manejar las acciones

    private void listarCantantes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cantante> cantantes = service.listarTodos();
        req.setAttribute("cantantes", cantantes);
        req.getRequestDispatcher("/WEB-INF/vistas/listarCantantes.jsp").forward(req, resp);
    }

    private void mostrarDetalleCantante(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Cantante> cantante = service.buscarPorId(id);
            if (cantante.isPresent()) {
                req.setAttribute("cantante", cantante.get());
                req.getRequestDispatcher("/WEB-INF/vistas/detalleCantante.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Cantante no encontrado.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para buscar al cantante.");
        }
    }

    private void guardarCantante(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

            String nombre = req.getParameter("nombre");
            String genero = req.getParameter("genero");
            String nacionalidad = req.getParameter("nacionalidad");

            if (nombre == null || nombre.isEmpty() || genero == null || genero.isEmpty() || nacionalidad == null || nacionalidad.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos insuficientes para guardar al cantante.");
                return;
            }

            Cantante cantante = new Cantante();
            cantante.setId(id);
            cantante.setNombre(nombre);

            service.guardar(cantante);
            resp.sendRedirect("CantanteServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para guardar al cantante.");
        }
    }

    private void eliminarCantante(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            service.eliminar(id);
            resp.sendRedirect("CantanteServlet?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para eliminar al cantante.");
        }
    }
}

