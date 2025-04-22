package com.radiolatino;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("✅ RadioLatino inicializado correctamente.");
        // Aquí puedes inicializar recursos globales, pools, logs, etc.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 RadioLatino detenido.");
        // Aquí podrías cerrar conexiones, limpiar recursos, etc.
    }
}

