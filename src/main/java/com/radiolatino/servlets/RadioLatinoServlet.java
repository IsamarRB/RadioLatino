package com.radiolatino.servlets;

import com.radiolatino.dao.UsuarioDAO;
import com.radiolatino.model.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/app")
public class RadioLatinoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EvaluacionPU");
        usuarioDAO = new UsuarioDAO(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "login":
                    procesarLogin(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no soportada");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
        }
    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");

        if (correo == null || correo.isEmpty()) {
            request.setAttribute("error", "Correo es requerido");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Usuario usuario = usuarioDAO.findByCorreo(correo);

        if (usuario != null) {
            request.getSession().setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/jsp/buscarEventos.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Correo no válido");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
