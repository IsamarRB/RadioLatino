package com.radiolatino.dao;

import com.radiolatino.model.Evento;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private DataSource dataSource;

    public EventoDAO() {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<Evento> listarTodos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT id, nombre, lugar, fecha, organizador FROM eventos";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getLong("id"));
                evento.setNombre(rs.getString("nombre"));
                evento.setLugar(rs.getString("lugar"));
                evento.setFecha(rs.getDate("fecha").toLocalDate());
                evento.setOrganizador(rs.getString("organizador"));
                eventos.add(evento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los eventos", e);
        }

        return eventos;
    }

    public Evento buscarPorId(Long id) {
        String sql = "SELECT id, nombre, lugar, fecha, organizador FROM eventos WHERE id = ?";
        Evento evento = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    evento = new Evento();
                    evento.setId(rs.getLong("id"));
                    evento.setNombre(rs.getString("nombre"));
                    evento.setLugar(rs.getString("lugar"));
                    evento.setFecha(rs.getDate("fecha").toLocalDate());
                    evento.setOrganizador(rs.getString("organizador"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el evento con ID " + id, e);
        }

        return evento;
    }

    public List<Evento> buscarPorCriterio(String criterio) {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT id, nombre, lugar, fecha, organizador FROM eventos " +
                "WHERE LOWER(nombre) LIKE ? OR LOWER(lugar) LIKE ? OR LOWER(organizador) LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String criterioLike = "%" + criterio.toLowerCase() + "%";
            stmt.setString(1, criterioLike);
            stmt.setString(2, criterioLike);
            stmt.setString(3, criterioLike);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getLong("id"));
                    evento.setNombre(rs.getString("nombre"));
                    evento.setLugar(rs.getString("lugar"));
                    evento.setFecha(rs.getDate("fecha").toLocalDate());
                    evento.setOrganizador(rs.getString("organizador"));
                    eventos.add(evento);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar eventos por criterio", e);
        }

        return eventos;
    }

    public void guardar(Evento evento) {
        String sql = evento.getId() == null
                ? "INSERT INTO eventos (nombre, lugar, fecha, organizador) VALUES (?, ?, ?, ?)"
                : "UPDATE eventos SET nombre = ?, lugar = ?, fecha = ?, organizador = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, evento.getNombre());
            stmt.setString(2, evento.getLugar());
            stmt.setDate(3, java.sql.Date.valueOf(evento.getFecha()));
            stmt.setString(4, evento.getOrganizador());

            if (evento.getId() != null) {
                stmt.setLong(5, evento.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el evento", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el evento con ID " + id, e);
        }
    }
}

