package com.cineflix.cineAPI.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
public class Cinema {

    public Cinema(){}
    public Cinema(Integer movieId, String title, String director, String studio, Set<String> movieCast, Integer movieYear, String poster) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.studio = studio;
        this.movieCast = movieCast;
        this.movieYear = movieYear;
        this.poster = poster;
    }

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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Set<String> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<String> movieCast) {
        this.movieCast = movieCast;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
