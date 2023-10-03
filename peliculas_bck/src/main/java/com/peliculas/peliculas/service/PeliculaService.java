package com.peliculas.peliculas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peliculas.peliculas.exception.PeliculaNotFoundException;
import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaService {
    private final PeliculaRepository peliculasRepository;

    public PeliculaService(PeliculaRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    public Pelicula addPelicula(Pelicula pelicula) {
        return peliculasRepository.save(pelicula);
    }

    public List<Pelicula> finAllPeliculas() {
        return peliculasRepository.findAll();
    }

    public Pelicula findPeliculaById(Long id){
        return peliculasRepository.findById(id)
        .orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado ninguna película con el id " + id));
    }

    public Pelicula updatePelicula(Pelicula pelicula) {
        return peliculasRepository.save(pelicula);
    }

    public void deletePelicula(Long id){
        peliculasRepository.deletePeliculaById(id);
    }
}
