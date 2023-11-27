package com.peliculas.peliculas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peliculas.peliculas.exception.PeliculaNotFoundException;
import com.peliculas.peliculas.model.Actor;
import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.ActorRepository;
import com.peliculas.peliculas.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaService {
    private final PeliculaRepository peliculasRepository;

    public PeliculaService(PeliculaRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    @Autowired
    ActorRepository actorRepository;

    public Pelicula addPelicula(Pelicula pelicula) {
        System.out.println("AddPelicula Service");
        return peliculasRepository.save(pelicula);
    }

    public List<Pelicula> finAllPeliculas() {
        return peliculasRepository.findAll();
    }

    public Pelicula findPeliculaById(Long id) {
        return peliculasRepository.findById(id)
                .orElseThrow(
                        () -> new PeliculaNotFoundException("No se ha encontrado ninguna película con el id " + id));
    }

    public Pelicula updatePelicula(Pelicula pelicula) {
        return peliculasRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculasRepository.deletePeliculaById(id);
    }

    public List<Pelicula> findPeliculaByActorName(String nombreActor) {
        Optional<Actor> actor = actorRepository.findByName(nombreActor);

        if (actor.isPresent()) {
            // Si se encuentra el actor, devuelve el conjunto de películas asociadas.
            List<Pelicula> peliculasList = new ArrayList<Pelicula>();
            peliculasList.addAll(actor.get().getPeliculas());
            return peliculasList;
        } else {
            // Si no se encuentra el actor, puedes decidir devolver un conjunto vacío o
            // lanzar una excepción.
            return new ArrayList<>(); // HashSet<>();
            // O lanzar una excepción, dependiendo de cómo quieras manejar esta situación.
            // throw new EntityNotFoundException("Actor con el nombre " + nombreActor + " no
            // encontrado.");
        }
    }

    public List<Pelicula> findPeliculaByActorName2(String nombreActor) {
        List<Pelicula> peliculas = new ArrayList<>();

        // Busca el actor por su nombre en la base de datos
        Optional<Actor> actor = actorRepository.findByName(nombreActor);

        if (actor != null) {
            // Obtiene las películas en las que participa el actor
            // Set<Pelicula> peliculasDelActor = actor.getPeliculas();
            // peliculas.addAll(peliculasDelActor);
        }

        return peliculas;
    }
}
