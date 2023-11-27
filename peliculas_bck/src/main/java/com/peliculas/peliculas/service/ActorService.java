package com.peliculas.peliculas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.peliculas.peliculas.exception.PeliculaNotFoundException;
import com.peliculas.peliculas.model.Actor;
import com.peliculas.peliculas.repository.ActorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ActorService {
    private ActorRepository actorRepository;

    public ActorService (ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> finAllActores() {
        return actorRepository.findAll();
    }

    public Actor findActorById(Long id){
        return actorRepository.findById(id)
        .orElseThrow(() -> new PeliculaNotFoundException("No se ha encontrado ningún actor con el id " + id));
    }

    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id){
        actorRepository.deleteActorById(id);
    }
}
