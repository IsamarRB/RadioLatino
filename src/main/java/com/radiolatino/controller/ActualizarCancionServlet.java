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

@WebServlet("/actualizarCancion")
public class ActualizarCancionServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        Long cantanteId = Long.parseLong(request.getParameter("cantanteId"));
        Long generoId = Long.parseLong(request.getParameter("generoId"));

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Cancion cancion = em.find(Cancion.class, id);
            cancion.setTitulo(titulo);
            cancion.setCantante(String.valueOf(em.find(Cantante.class, cantanteId)));
            cancion.setGenero(String.valueOf(em.find(Genero.class, generoId)));

            em.getTransaction().commit();

        } finally {
            em.close();
        }

        response.sendRedirect("canciones");
    }
}

