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
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no soportada.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada.");
        }
    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");

        // Validación del correo
        if (correo == null || correo.isEmpty()) {
            request.setAttribute("error", "El campo de correo es obligatorio.");
            request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
            return;
        }

        Usuario usuario = usuarioDAO.findByCorreo(correo);

        if (usuario != null) {
            // Usuario encontrado
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("/WEB-INF/BuscarEventos.jsp"); // Redirige al dashboard o página principal.
        } else {
            // Usuario no encontrado
            request.setAttribute("error", "Correo no registrado.");
            request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
        }
    }
}
