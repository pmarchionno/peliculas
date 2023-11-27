package com.peliculas.peliculas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.peliculas.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    public Optional<Actor> findById(int id);

    public Optional<Actor> findByName(String titlo);

    void deleteActorById(Long id);
}
