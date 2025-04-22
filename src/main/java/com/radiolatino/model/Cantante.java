package com.radiolatino.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cantantes")
public class Cantante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "cantante")
    private List<Cancion> canciones;

    public Cantante() {}

    public Cantante(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}