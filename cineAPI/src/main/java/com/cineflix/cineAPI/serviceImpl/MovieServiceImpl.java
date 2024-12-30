package com.cineflix.cineAPI.serviceImpl;

import com.cineflix.cineAPI.dto.CinemaDto;
import com.cineflix.cineAPI.dto.MoviePageResponse;
import com.cineflix.cineAPI.entities.Cinema;
import com.cineflix.cineAPI.exceptions.FileExistsException;
import com.cineflix.cineAPI.exceptions.MovieNotFoundException;
import com.cineflix.cineAPI.repository.CinemaRepo;
import com.cineflix.cineAPI.service.FileService;
import com.cineflix.cineAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final CinemaRepo cinemaRepo;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseurl;

    public MovieServiceImpl(CinemaRepo cinemaRepo, FileService fileService) {
        this.cinemaRepo = cinemaRepo;
        this.fileService = fileService;
    }

    @Override
    public CinemaDto addMovie(CinemaDto cinemaDto, MultipartFile file) throws IOException {
        if(Files.exists(Paths.get(path+ File.separator+file.getOriginalFilename()))){
            throw new FileExistsException("File Name already exists, Please provide other name");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        cinemaDto.setPoster(uploadedFileName);
        Cinema cinema = new Cinema(
                null,
                cinemaDto.getTitle(),
                cinemaDto.getDirector(),
                cinemaDto.getStudio(),
                cinemaDto.getMovieCast(),
                cinemaDto.getMovieYear(),
                cinemaDto.getPoster()
        );
        Cinema savedMovie = cinemaRepo.save(cinema);
        String posterUrl = baseurl+"/file/"+uploadedFileName;
        CinemaDto response = new CinemaDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getMovieYear(),
                savedMovie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public CinemaDto getMovie(Integer cinemaId) {
        Cinema cinema = cinemaRepo.findById(cinemaId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        String posterUrl = baseurl+"/file/"+cinema.getPoster();
        CinemaDto response = new CinemaDto(
                cinema.getMovieId(),
                cinema.getTitle(),
                cinema.getDirector(),
                cinema.getStudio(),
                cinema.getMovieCast(),
                cinema.getMovieYear(),
                cinema.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public List<CinemaDto> getAllMovies() {
        List<Cinema> cinemas = cinemaRepo.findAll();
        List<CinemaDto> cinemaDtos = new ArrayList<>();
        for (Cinema cinema : cinemas){
            String posterUrl = baseurl+"/file/"+cinema.getPoster();
            CinemaDto cinemaDto = new CinemaDto(
                    cinema.getMovieId(),
                    cinema.getTitle(),
                    cinema.getDirector(),
                    cinema.getStudio(),
                    cinema.getMovieCast(),
                    cinema.getMovieYear(),
                    cinema.getPoster(),
                    posterUrl
            );
            cinemaDtos.add(cinemaDto);
        }
        return cinemaDtos;
    }

    @Override
    public CinemaDto updateMovie(Integer cinemaId, CinemaDto cinemaDto, MultipartFile file) throws IOException {
        Cinema cn = cinemaRepo.findById(cinemaId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        String fileName = cn.getPoster();
        if(file!=null){
            Files.deleteIfExists(Paths.get(path+ File.separator+fileName));
            fileName = fileService.uploadFile(path, file);
        }
        cinemaDto.setPoster(fileName);
        Cinema cinema = new Cinema(
                cn.getMovieId(),
                cinemaDto.getTitle(),
                cinemaDto.getDirector(),
                cinemaDto.getStudio(),
                cinemaDto.getMovieCast(),
                cinemaDto.getMovieYear(),
                cinemaDto.getPoster()
        );
        String posterUrl = baseurl+"/file/"+fileName;
        Cinema updatedCinema = cinemaRepo.save(cinema);
        CinemaDto response = new CinemaDto(
                updatedCinema.getMovieId(),
                updatedCinema.getTitle(),
                updatedCinema.getDirector(),
                updatedCinema.getStudio(),
                updatedCinema.getMovieCast(),
                updatedCinema.getMovieYear(),
                updatedCinema.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public String deleteMovie(Integer cinemaId) throws IOException {
        Cinema cinema = cinemaRepo.findById(cinemaId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Files.deleteIfExists(Paths.get(path+ File.separator+cinema.getPoster()));
        cinemaRepo.deleteById(cinema.getMovieId());
        return "Deleted the movie with ID : "+cinema.getMovieId();
    }

    @Override
    public MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        return null;
    }
}
