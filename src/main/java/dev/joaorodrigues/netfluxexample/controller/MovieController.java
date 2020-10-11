package dev.joaorodrigues.netfluxexample.controller;

import dev.joaorodrigues.netfluxexample.model.Movie;
import dev.joaorodrigues.netfluxexample.model.MovieEvent;
import dev.joaorodrigues.netfluxexample.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> streamMovieEvents(@PathVariable String id){
        return movieService.events(id);
    }

    @GetMapping(value = "/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
