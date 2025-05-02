package com.radiolatino.servlets;

import com.radiolatino.model.Usuario;
import com.radiolatino.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada.");
            return;
        }

        switch (accion) {
            case "login":
                procesarLogin(req, resp);
                break;
            case "logout":
                procesarLogout(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida.");
        }
    }

    private void procesarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        // Validación de los parámetros
        if (usuario == null || usuario.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("error", "Usuario y contraseña son obligatorios.");
            req.getRequestDispatcher("/webapp/Login.jsp").forward(req, resp);
            return;
        }

        Usuario u = usuarioService.login(usuario, password);

        if (u != null) {
            // Usuario autenticado correctamente
            req.getSession().setAttribute("usuario", u);
            resp.sendRedirect("/WEB-INF/views/BuscarEventos.jsp");
        } else {
            // Usuario o contraseña incorrectos
            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("/webapp/Login.jsp").forward(req, resp);
        }
    }

    private void procesarLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate(); // Invalida la sesión
        resp.sendRedirect("/webapp/Login.jsp"); // Redirige a la página de login
    }
}
