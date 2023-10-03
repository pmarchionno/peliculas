package com.peliculas.peliculas.resource;

import java.util.List;

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

import com.peliculas.peliculas.model.Pelicula;
import com.peliculas.peliculas.service.PeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaResource {
    private final PeliculaService peliculaService;

    
    public PeliculaResource(PeliculaService peliculasService) {
        this.peliculaService = peliculasService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.finAllPeliculas();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pelicula> getpeliclaById (@PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findPeliculaById(id);
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Pelicula> addPelicula(@RequestBody Pelicula pelicula) {
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
}
