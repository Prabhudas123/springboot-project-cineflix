package com.cineflix.cineAPI.repository;

import com.cineflix.cineAPI.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepo extends JpaRepository<Cinema,Integer> {
}
