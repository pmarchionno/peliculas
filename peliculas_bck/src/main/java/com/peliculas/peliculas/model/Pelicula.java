package com.peliculas.peliculas.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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

    
    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, Long anio, String trama, Double presupuesto) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.trama = trama;
        this.presupuesto = presupuesto;
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
