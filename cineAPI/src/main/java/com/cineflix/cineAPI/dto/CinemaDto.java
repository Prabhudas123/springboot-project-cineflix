package com.cineflix.cineAPI.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
