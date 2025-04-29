package com.radiolatino.dao;

import com.radiolatino.model.Genero;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    private DataSource dataSource;

    public GeneroDAO() {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<Genero> listarTodos() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT id, nombre FROM generos";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setId(rs.getLong("id"));
                genero.setNombre(rs.getString("nombre"));
                generos.add(genero);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los géneros", e);
        }

        return generos;
    }

    public Genero buscarPorId(Long id) {
        String sql = "SELECT id, nombre FROM generos WHERE id = ?";
        Genero genero = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    genero = new Genero();
                    genero.setId(rs.getLong("id"));
                    genero.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el género con ID " + id, e);
        }

        return genero;
    }

    public void guardar(Genero genero) {
        String sql = genero.getId() == null
                ? "INSERT INTO generos (nombre) VALUES (?)"
                : "UPDATE generos SET nombre = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, genero.getNombre());
            if (genero.getId() != null) {
                stmt.setLong(2, genero.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el género", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM generos WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el género con ID " + id, e);
        }
    }
}

