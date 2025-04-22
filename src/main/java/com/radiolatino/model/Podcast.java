package com.radiolatino.model;

import jakarta.persistence.*;

@Entity
@Table(name = "podcasts")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String urlAudio;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public Podcast() {}

    public Podcast(String titulo, String urlAudio, Genero genero) {
        this.titulo = titulo;
        this.urlAudio = urlAudio;
        this.genero = genero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getUrlAudio() { return urlAudio; }
    public void setUrlAudio(String urlAudio) { this.urlAudio = urlAudio; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
}
