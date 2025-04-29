package com.radiolatino.servlets;

import com.radiolatino.model.GrupoMusical;
import com.radiolatino.service.GrupoMusicalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/GestionGrupos")
public class ControladorGestionGrupos extends HttpServlet {
    private final GrupoMusicalService grupoService = new GrupoMusicalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no proporcionada.");
            return;
        }

        switch (accion) {
            case "listar":
                listarGrupos(req, resp);
                break;
            case "detalle":
                mostrarDetalle(req, resp);
                break;
            case "eliminar":
                eliminarGrupo(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción GET no reconocida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if ("guardar".equals(accion)) {
            guardarGrupo(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción POST no reconocida.");
        }
    }

    // Métodos privados
    private void listarGrupos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrupoMusical> grupos = grupoService.listarTodos();
        req.setAttribute("grupos", grupos);
        req.getRequestDispatcher("/WEB-INF/ListarGrupos.jsp").forward(req, resp);
    }

    private void mostrarDetalle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<GrupoMusical> grupo = grupoService.buscarPorId(id);
            if (grupo.isPresent()) {
                req.setAttribute("grupo", grupo.get());
                req.getRequestDispatcher("/WEB-INF/DetalleGrupo.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Grupo musical no encontrado.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para buscar el grupo musical.");
        }
    }

    private void guardarGrupo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        String genero = req.getParameter("genero");
        String integrantes = req.getParameter("integrantes");

        if (nombre == null || nombre.isEmpty() || genero == null || genero.isEmpty() || integrantes == null || integrantes.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos insuficientes para guardar el grupo musical.");
            return;
        }

        GrupoMusical grupo = new GrupoMusical();
        grupo.setNombre(nombre);
        grupo.setGenero(genero);
        grupo.setIntegrantes(integrantes);

        grupoService.guardar(grupo);
        resp.sendRedirect("GestionGrupos?accion=listar");
    }

    private void eliminarGrupo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            grupoService.eliminar(id);
            resp.sendRedirect("GestionGrupos?accion=listar");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido para eliminar el grupo musical.");
        }
    }
}
