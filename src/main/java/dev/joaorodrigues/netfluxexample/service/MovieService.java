package dev.joaorodrigues.netfluxexample.service;

import dev.joaorodrigues.netfluxexample.model.Movie;
import dev.joaorodrigues.netfluxexample.model.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();
}
