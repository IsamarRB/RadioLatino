package com.radiolatino.controller;


import com.radiolatino.dao.CancionDAO;
import com.radiolatino.model.Cancion;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/canciones")
public class CancionServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CancionDAO cancionDAO = new CancionDAO(emf);
        List<Cancion> canciones = cancionDAO.findAll();

        request.setAttribute("canciones", canciones);
        request.getRequestDispatcher("/WEB-INF/jsp/canciones.jsp").forward(request, response);
    }
}

