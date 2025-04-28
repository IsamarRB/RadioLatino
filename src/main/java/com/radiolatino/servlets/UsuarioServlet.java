package com.radiolatino.servlets;

import com.radiolatino.model.Usuario;
import com.radiolatino.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("login".equals(accion)) {
            String usuario = req.getParameter("usuario");
            String password = req.getParameter("password");

            Usuario u = usuarioService.login(usuario, password);

            if (u != null) {
                req.getSession().setAttribute("usuario", u);
                resp.sendRedirect("BuscarEventos.jsp");
            } else {
                req.setAttribute("error", "Usuario o contraseña incorrectos");
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
        }

        if ("logout".equals(accion)) {
            req.getSession().invalidate();
            resp.sendRedirect("Login.jsp");
        }
    }
}

