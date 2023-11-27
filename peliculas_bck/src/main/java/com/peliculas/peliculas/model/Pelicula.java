package com.peliculas.peliculas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id",
scope = Pelicula.class)
// @JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "id")
// @JsonFilter("actorFilter")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 60)
    private String titulo;

    @Column(name = "anio", nullable = false)
    private Long anio;

    @Column(name = "trama", nullable = false, length = 200)
    private String trama;

    @Column(name = "presupuesto", nullable = true, scale = 2)
    private Double presupuesto;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "pelicula_actor", joinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    // @JsonManagedReference(value = "pelicula_actor")
    private List<Actor> actores = new ArrayList<>();

    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, Long anio, String trama, Double presupuesto, List<Actor> actores) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.trama = trama;
        this.presupuesto = presupuesto;
        this.actores = actores;
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

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    @Override
    public String toString() {
        return "Película{" +
                "id=" + id +
                ", Titulo = '" + titulo + '\'' +
                ", Año = " + anio +
                ", Trama = '" + trama + '\'' +
                ", Presupuesto = '" + presupuesto + '\'' +
                '}';
    }

}
