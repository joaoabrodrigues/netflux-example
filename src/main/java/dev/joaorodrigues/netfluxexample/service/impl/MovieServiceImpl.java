package dev.joaorodrigues.netfluxexample.service.impl;

import dev.joaorodrigues.netfluxexample.model.Movie;
import dev.joaorodrigues.netfluxexample.model.MovieEvent;
import dev.joaorodrigues.netfluxexample.repository.MovieRepository;
import dev.joaorodrigues.netfluxexample.service.MovieService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink ->
                movieEventSynchronousSink.next(new MovieEvent(movieId, LocalDateTime.now()))
             ).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
