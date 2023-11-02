package com.peliculas.peliculas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.peliculas.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    public Optional<Pelicula> findById(int id);

    public Optional<Pelicula> findByTitulo(String titlo);

    void deletePeliculaById(Long id); // Es equivalente a poner solamete: void deleteById(Long id);
}
