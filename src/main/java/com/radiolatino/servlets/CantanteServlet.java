package com.radiolatino.servlets;


import com.radiolatino.model.Cantante;
import com.radiolatino.service.CantanteService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/CantanteServlet")
public class CantanteServlet extends HttpServlet {
    private final CantanteService service = new CantanteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("listar".equals(accion)) {
            List<Cantante> cantantes = service.listarTodos();
            req.setAttribute("cantantes", cantantes);
            req.getRequestDispatcher("/WEB-INF/vistas/listarCantantes.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crear, editar, eliminar
    }
}
