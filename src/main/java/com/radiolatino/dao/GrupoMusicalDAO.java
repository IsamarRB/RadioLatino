package com.radiolatino.dao;

import com.radiolatino.model.GrupoMusical;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoMusicalDAO {

    private DataSource dataSource;

    public GrupoMusicalDAO() {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<GrupoMusical> listarTodos() {
        List<GrupoMusical> grupos = new ArrayList<>();
        String sql = "SELECT id, nombre, genero, integrantes FROM grupos_musicales";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GrupoMusical grupo = new GrupoMusical();
                grupo.setId(rs.getLong("id"));
                grupo.setNombre(rs.getString("nombre"));
                grupo.setGenero(rs.getString("genero"));
                grupo.setIntegrantes(rs.getString("integrantes"));
                grupos.add(grupo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los grupos musicales", e);
        }

        return grupos;
    }

    public GrupoMusical buscarPorId(Long id) {
        String sql = "SELECT id, nombre, genero, integrantes FROM grupos_musicales WHERE id = ?";
        GrupoMusical grupo = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    grupo = new GrupoMusical();
                    grupo.setId(rs.getLong("id"));
                    grupo.setNombre(rs.getString("nombre"));
                    grupo.setGenero(rs.getString("genero"));
                    grupo.setIntegrantes(rs.getString("integrantes"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el grupo musical con ID " + id, e);
        }

        return grupo;
    }

    public void guardar(GrupoMusical grupo) {
        String sql = grupo.getId() == null
                ? "INSERT INTO grupos_musicales (nombre, genero, integrantes) VALUES (?, ?, ?)"
                : "UPDATE grupos_musicales SET nombre = ?, genero = ?, integrantes = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getGenero());
            stmt.setString(3, grupo.getIntegrantes());
            if (grupo.getId() != null) {
                stmt.setLong(4, grupo.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el grupo musical", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM grupos_musicales WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el grupo musical con ID " + id, e);
        }
    }
}
