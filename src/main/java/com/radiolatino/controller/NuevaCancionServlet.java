package com.radiolatino.controller;


import com.radiolatino.model.Cantante;
import com.radiolatino.model.Genero;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.radiolatino.dao.GeneroDAO;
import com.radiolatino.dao.CantanteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/nuevaCancion")  // Eel patrón URL sea correcto
public class NuevaCancionServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CantanteDAO cantanteDAO = new CantanteDAO(emf);
        GeneroDAO generoDAO = new GeneroDAO(emf);

        List<Cantante> cantantes = cantanteDAO.findAll();
        List<Genero> generos = generoDAO.findAll();

        request.setAttribute("cantantes", cantantes);
        request.setAttribute("generos", generos);

        request.getRequestDispatcher("/WEB-INF/jsp/nuevaCancion.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();  // Liberar recursos cuando el servlet se destruye
        }
    }
}
