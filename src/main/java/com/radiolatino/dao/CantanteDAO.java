package com.radiolatino.dao;

import com.radiolatino.model.Cantante;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CantanteDAO {

    private DataSource dataSource;

    public CantanteDAO() {
        try {
            // Busca el DataSource definido en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<Cantante> findAll() {
        List<Cantante> cantantes = new ArrayList<>();
        String sql = "SELECT id, nombre, genero, nacionalidad FROM cantantes";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cantante cantante = new Cantante();
                cantante.setId(rs.getLong("id"));
                cantante.setNombre(rs.getString("nombre"));
                cantantes.add(cantante);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los cantantes", e);
        }

        return cantantes;
    }

    public Cantante buscarPorId(Long id) {
        String sql = "SELECT id, nombre, genero, nacionalidad FROM cantantes WHERE id = ?";
        Cantante cantante = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cantante = new Cantante();
                    cantante.setId(rs.getLong("id"));
                    cantante.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el cantante con ID " + id, e);
        }

        return cantante;
    }

    public List<Cantante> buscarPorNombre(String nombre) {
        List<Cantante> cantantes = new ArrayList<>();
        String sql = "SELECT id, nombre, genero, nacionalidad FROM cantantes WHERE LOWER(nombre) LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre.toLowerCase() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cantante cantante = new Cantante();
                    cantante.setId(rs.getLong("id"));
                    cantante.setNombre(rs.getString("nombre"));
                    cantantes.add(cantante);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cantantes por nombre", e);
        }

        return cantantes;
    }

    public void guardar(Cantante cantante) {
        String sql = cantante.getId() == null
                ? "INSERT INTO cantantes (nombre, genero, nacionalidad) VALUES (?, ?, ?)"
                : "UPDATE cantantes SET nombre = ?, genero = ?, nacionalidad = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cantante.getNombre());
            if (cantante.getId() != null) {
                stmt.setLong(4, cantante.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el cantante", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM cantantes WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cantante con ID " + id, e);
        }
    }
}


