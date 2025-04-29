package com.radiolatino.servlets;

import com.radiolatino.model.Usuario;
import com.radiolatino.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/InicioSesion")
public class ControladorInicioSesion extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Validar parámetros enviados por el usuario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros de inicio de sesión incompletos.");
            return;
        }

        // Lógica de autenticación
        Optional<Usuario> usuarioOpt = usuarioService.autenticar(username, password);

        if (usuarioOpt.isPresent()) {
            // Si es válido, iniciar sesión
            req.getSession().setAttribute("usuario", usuarioOpt.get());
            resp.sendRedirect("Inicio.jsp"); // Redirigir al inicio
        } else {
            // Manejar credenciales incorrectas
            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }
}

