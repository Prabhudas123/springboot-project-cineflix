package com.cineflix.cineAPI.controllers;

import com.cineflix.cineAPI.dto.CinemaDto;
import com.cineflix.cineAPI.dto.MoviePageResponse;
import com.cineflix.cineAPI.service.MovieService;
import com.cineflix.cineAPI.utils.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class CinemaController {

    private final MovieService movieService;

    public CinemaController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add-movie")
    public ResponseEntity<CinemaDto> addMovieHandler(@RequestPart MultipartFile file,
                                                     @RequestPart String cinemaDto) throws IOException {
        CinemaDto dto = convertToDto(cinemaDto);
        return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<CinemaDto> getMovieById(@PathVariable Integer movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CinemaDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @PutMapping("/update/{movieId}")
    public ResponseEntity<CinemaDto> updateMovieHandler(@PathVariable Integer movieId,
                                                        @RequestPart MultipartFile file,
                                                        @RequestPart String cinemaDtoObj) throws IOException {
        CinemaDto cinemaDto = convertToDto(cinemaDtoObj);
        if(file.isEmpty()) file=null;
        return ResponseEntity.ok(movieService.updateMovie(movieId,cinemaDto,file));
    }
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
    @GetMapping("/getAllPage")
    public ResponseEntity<MoviePageResponse> getAllMoviesByPagination(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize
    ){
        return ResponseEntity.ok(movieService.getAllMoviesWithPagination(pageNumber,pageSize));
    }

    @GetMapping("/getAllPageoSort")
    public ResponseEntity<MoviePageResponse> getAllMoviesByPaginationAndSorting(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortDir
    ){
        return ResponseEntity.ok(movieService.getAllMoviesWithPaginationAndSorting(pageNumber,pageSize,sortBy,sortDir));
    }

    public CinemaDto convertToDto(String cinemaDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(cinemaDto,CinemaDto.class);
    }
}
