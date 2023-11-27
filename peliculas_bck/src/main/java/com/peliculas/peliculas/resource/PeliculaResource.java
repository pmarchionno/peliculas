package com.peliculas.peliculas.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.model.Actor;
import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.repository.ActorRepository;
import com.peliculas.peliculas.service.PeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaResource {
    private final PeliculaService peliculaService;

    @Autowired
    private ActorRepository actorRepository;

    public PeliculaResource(PeliculaService peliculasService) {
        this.peliculaService = peliculasService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.finAllPeliculas();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findPeliculaById(id);
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Pelicula> addPelicula(@RequestBody Pelicula pelicula) {
        for (Actor actor : pelicula.getActores()) {
            // Busca el actor en la base de datos por su ID o nombre, por ejemplo, usando el
            // ActorRepository.
            Actor existingActor = actorRepository.findById(actor.getId()).orElse(null);
            if (existingActor != null) {
                // Actualiza la referencia del actor en la película con el actor existente.
                actor = existingActor;
            } else {
                // Manejar el caso en el que el actor no existe en la base de datos.
                // Puedes lanzar una excepción, devolver un error, o realizar alguna acción
                // personalizada.
                // Aquí, asumiré que los actores no existentes se agregarán a la película.
            }
        }
        Pelicula newpPelicula = peliculaService.addPelicula(pelicula);
        return new ResponseEntity<>(newpPelicula, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Pelicula> updatePelicula(@RequestBody Pelicula pelicula) {
        Pelicula updatePelicula = peliculaService.updatePelicula(pelicula);
        return new ResponseEntity<>(updatePelicula, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable("id") Long id) {
        peliculaService.deletePelicula(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filmsByActor/{name}")
    public ResponseEntity<List<Pelicula>> getpeliculaByActorName(@PathVariable("name") String name) {
        List<Pelicula> peliculas = new ArrayList<>(); // peliculaService.findPeliculaByActorName(name);
        peliculas = peliculaService.findPeliculaByActorName(name);
        return new ResponseEntity<>(peliculas, HttpStatus.OK);

        // List<Pelicula> peliculas = peliculaService.finAllPeliculas();
        // return new ResponseEntity<>(peliculas, HttpStatus.OK);

        // return null;
    }

    @GetMapping("/actoresFilm/{id}")
    public ResponseEntity<List<Actor>> getActoresByPeliculaId(@PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findPeliculaById(id);
        return new ResponseEntity<>(pelicula.getActores(), HttpStatus.OK);
    }
}
