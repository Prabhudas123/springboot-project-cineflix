package com.cineflix.cineAPI.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's title")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's title")
    private String director;
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's title")
    private String studio;
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @Column(nullable = false)
    private Integer movieYear;
    @Column(nullable = false)
    @NotBlank(message = "Please provide Poster")
    private String poster;
}