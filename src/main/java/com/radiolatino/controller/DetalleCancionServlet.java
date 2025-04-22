package com.radiolatino.controller;


import com.radiolatino.model.Cancion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cancion")
public class DetalleCancionServlet extends HttpServlet {

    private EntityManagerFactory emf;
    private Class<? extends Object> Cantante;
    private Class<? extends Object> Genero;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("RadioNicaPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        Long id = Long.parseLong(request.getParameter("id"));

        EntityManager em = emf.createEntityManager();
        Cancion cancion = em.find(Cancion.class, id);
        em.close();

        if ("detalle".equals(accion)) {
            request.setAttribute("cancion", cancion);
            request.getRequestDispatcher("/WEB-INF/jsp/detalleCancion.jsp").forward(request, response);
        } else if ("editar".equals(accion)) {
            request.setAttribute("cancion", cancion);

            EntityManager em2 = emf.createEntityManager();
            request.setAttribute("cantantes", em2.createQuery("SELECT c FROM Cantante c", Cantante).getResultList());
            request.setAttribute("generos", em2.createQuery("SELECT g FROM Genero g", Genero).getResultList());
            em2.close();

            request.getRequestDispatcher("/WEB-INF/jsp/editarCancion.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            EntityManager em3 = emf.createEntityManager();
            em3.getTransaction().begin();
            Cancion toDelete = em3.find(Cancion.class, id);
            em3.remove(toDelete);
            em3.getTransaction().commit();
            em3.close();
            response.sendRedirect("canciones");
        }
    }
}