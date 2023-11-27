package com.peliculas.peliculas.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.model.Actor;
import com.peliculas.peliculas.service.ActorService;

@RestController
@RequestMapping("/actores")
public class ActorResource {
    private final ActorService actorService;

    public ActorResource(ActorService actorService) {
        this.actorService = actorService;
    }

@GetMapping("/all")
    public ResponseEntity<List<Actor>> getAllActores() {
        List<Actor> actores = actorService.finAllActores();
        return new ResponseEntity<>(actores, HttpStatus.OK);
    }
    
}
