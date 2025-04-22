package com.radiolatino.servlets;


import com.radiolatino.model.Cancion;
import com.radiolatino.service.CancionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/CancionServlet")
public class CancionServlet extends HttpServlet {
    private CancionService cancionService = new CancionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("listar".equals(accion)) {
            List<Cancion> canciones = cancionService.listarTodos();
            req.setAttribute("canciones", canciones);
            req.getRequestDispatcher("/WEB-INF/vistas/listarCanciones.jsp").forward(req, resp);
        }

        if ("detalle".equals(accion)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Cancion c = cancionService.buscarPorId(id);
            req.setAttribute("cancion", c);
            req.getRequestDispatcher("/WEB-INF/vistas/detalleCancion.jsp").forward(req, resp);
        }

        // Puedes agregar editar, eliminar, etc.
    }
}

