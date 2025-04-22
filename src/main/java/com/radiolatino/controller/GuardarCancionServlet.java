package com.radiolatino.controller;


import com.radiolatino.model.Cancion;
import com.radiolatino.model.Cantante;
import com.radiolatino.model.Genero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/guardarCancion")
public class GuardarCancionServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        Long cantanteId = Long.parseLong(request.getParameter("cantanteId"));
        Long generoId = Long.parseLong(request.getParameter("generoId"));

        EntityManager em = emf.createEntityManager();

        try {
            Cantante cantante = em.find(Cantante.class, cantanteId);
            Genero genero = em.find(Genero.class, generoId);

            Cancion nueva = new Cancion();

            em.getTransaction().begin();
            em.persist(nueva);
            em.getTransaction().commit();

            response.sendRedirect("canciones");

        } finally {
            em.close();
        }
    }
}
