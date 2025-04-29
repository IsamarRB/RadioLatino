package com.radiolatino.dao;

import com.radiolatino.model.Podcast;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PodcastDAO {

    private DataSource dataSource;

    public PodcastDAO() {
        try {
            // Busca el DataSource configurado en context.xml
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/emisoradb2");
        } catch (NamingException e) {
            throw new RuntimeException("No se pudo encontrar el DataSource", e);
        }
    }

    public List<Podcast> listarTodos() {
        List<Podcast> podcasts = new ArrayList<>();
        String sql = "SELECT id, titulo, descripcion, url FROM podcasts";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Podcast podcast = new Podcast();
                podcast.setId(rs.getLong("id"));
                podcast.setTitulo(rs.getString("titulo"));
                podcast.setGenero(rs.getString("genero"));
                podcast.setUrlAudio(rs.getString("urlAudio"));
                podcasts.add(podcast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los podcasts", e);
        }

        return podcasts;
    }

    public Podcast buscarPorId(Long id) {
        String sql = "SELECT id, titulo, descripcion, url FROM podcasts WHERE id = ?";
        Podcast podcast = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    podcast = new Podcast();
                    podcast.setId(rs.getLong("id"));
                    podcast.setTitulo(rs.getString("titulo"));
                    podcast.setGenero(rs.getString("genero"));
                    podcast.setUrlAudio(rs.getString("urlAudio"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el podcast con ID " + id, e);
        }

        return podcast;
    }

    public void guardar(Podcast podcast) {
        String sql = podcast.getId() == null
                ? "INSERT INTO podcasts (titulo, descripcion, url) VALUES (?, ?, ?)"
                : "UPDATE podcasts SET titulo = ?, descripcion = ?, url = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, podcast.getTitulo());
            stmt.setString(2, podcast.getGenero());
            stmt.setString(3, podcast.getUrlAudio());
            if (podcast.getId() != null) {
                stmt.setLong(4, podcast.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el podcast", e);
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM podcasts WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el podcast con ID " + id, e);
        }
    }
}



