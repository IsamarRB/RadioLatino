package com.radiolatino.dao;

import com.radiolatino.model.Usuario;
import jakarta.persistence.EntityManagerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private DataSource dataSource;

    public UsuarioDAO(EntityManagerFactory emf) {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public Usuario findByCorreo(String correo) {
        String sql = "SELECT id, nombre, correo, password FROM usuarios WHERE correo = ?";
        Usuario usuario = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario con correo: " + correo, e);
        }

        return usuario;
    }
}


