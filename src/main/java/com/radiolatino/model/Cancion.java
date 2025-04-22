package com.radiolatino.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cancion")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String cantante;

    @Column(nullable = false, unique = true)
    private String genero;

    @OneToOne(mappedBy = "cancion")
    private List<Cantante> canciones;

    public Cancion() {}

    public Cancion(String nombre) { this.titulo= titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Cantante> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cantante> canciones) {
        this.canciones = canciones;
    }
}
