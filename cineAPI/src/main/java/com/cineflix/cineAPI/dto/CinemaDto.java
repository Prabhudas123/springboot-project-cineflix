package com.cineflix.cineAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
public class CinemaDto {

    private Integer movieId;
    @NotBlank(message = "Please provide movie's title")
    private String title;
    @NotBlank(message = "Please provide movie's title")
    private String director;
    @NotBlank(message = "Please provide movie's title")
    private String studio;
    private Set<String> movieCast;
    private Integer movieYear;
    @NotBlank(message = "Please provide movie's Poster")
    private String poster;
    @NotBlank(message = "Please provide the poster URL")
    private String posterUrl;

    public CinemaDto(){}
    public CinemaDto(Integer movieId, String title, String director, String studio, Set<String> movieCast, Integer movieYear, String poster, String posterUrl) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.studio = studio;
        this.movieCast = movieCast;
        this.movieYear = movieYear;
        this.poster = poster;
        this.posterUrl = posterUrl;
    }

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

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
