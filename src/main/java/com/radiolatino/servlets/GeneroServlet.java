package com.radiolatino.servlets;


import com.radiolatino.model.Genero;
import com.radiolatino.service.GeneroService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/GeneroServlet")
public class GeneroServlet extends HttpServlet {
    private final GeneroService service = new GeneroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("listar".equals(accion)) {
            List<Genero> generos = service.listarTodos();
            req.setAttribute("generos", generos);
            req.getRequestDispatcher("/WEB-INF/vistas/listarGeneros.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crear, editar, eliminar
    }
}

