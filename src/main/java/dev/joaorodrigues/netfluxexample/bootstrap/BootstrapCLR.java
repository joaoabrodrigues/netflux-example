package dev.joaorodrigues.netfluxexample.bootstrap;

import dev.joaorodrigues.netfluxexample.model.Movie;
import dev.joaorodrigues.netfluxexample.repository.MovieRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private static final Log LOG = LogFactory.getLog(BootstrapCLR.class);

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {
        movieRepository.deleteAll()
                .thenMany(saveMovies())
                .thenMany(findAllMovies())
                .flatMap(printAllMovies())
                .subscribe();
    }

    private Flux<Movie> saveMovies() {
        return Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                "Back to the Future", "Meet the Fluxes", "Lord of the Fluxes")
                .map(Movie::new)
                .flatMap(movieRepository::save);
    }

    private Flux<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    private Function<Movie, Publisher<? extends Movie>> printAllMovies() {
        return movie -> Mono.just(movie).doOnNext(LOG::info).thenReturn(movie);
    }
}
