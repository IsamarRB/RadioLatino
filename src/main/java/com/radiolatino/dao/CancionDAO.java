package com.radiolatino.dao;

import com.radiolatino.model.Cancion;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {

    private DataSource dataSource;

    public CancionDAO() {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<Cancion> listarTodos() {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, cantante FROM canciones";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cancion cancion = new Cancion();
                cancion.setId(rs.getLong("id"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setGenero(rs.getString("genero"));
                cancion.setCantante(rs.getString("cantante"));
                canciones.add(cancion);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las canciones", e);
        }

        return canciones;
    }

    public Cancion buscarPorId(Long id) {
        String sql = "SELECT id, titulo, genero, cantante FROM canciones WHERE id = ?";
        Cancion cancion = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cancion = new Cancion();
                    cancion.setId(rs.getLong("id"));
                    cancion.setTitulo(rs.getString("titulo"));
                    cancion.setGenero(rs.getString("genero"));
                    cancion.setCantante(rs.getString("cantante"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la canción con ID " + id, e);
        }

        return cancion;
    }

    public void guardar(Cancion cancion) {
        String sql = cancion.getId() == null
                ? "INSERT INTO canciones (titulo, genero, cantante) VALUES (?, ?, ?)"
                : "UPDATE canciones SET titulo = ?, genero = ?, cantante = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cancion.getTitulo());
            stmt.setString(2, cancion.getGenero());
            stmt.setString(3, cancion.getCantante());
            if (cancion.getId() != null) {
                stmt.setLong(4, cancion.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la canción", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM canciones WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la canción con ID " + id, e);
        }
    }
}

