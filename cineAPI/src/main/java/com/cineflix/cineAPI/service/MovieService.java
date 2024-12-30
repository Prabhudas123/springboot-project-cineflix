package com.cineflix.cineAPI.service;

import com.cineflix.cineAPI.dto.CinemaDto;
import com.cineflix.cineAPI.dto.MoviePageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    CinemaDto addMovie(CinemaDto cinemaDto, MultipartFile file) throws IOException;
    CinemaDto getMovie(Integer cinemaId);

    List<CinemaDto> getAllMovies();

    CinemaDto updateMovie(Integer cinemaId, CinemaDto cinemaDto, MultipartFile file) throws IOException;

    String deleteMovie(Integer cinemaId) throws IOException;

    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize);

    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber,
                                                           Integer pageSize,
                                                           String sortBy,
                                                           String dir);

}
