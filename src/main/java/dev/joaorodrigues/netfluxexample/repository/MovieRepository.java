package dev.joaorodrigues.netfluxexample.repository;

import dev.joaorodrigues.netfluxexample.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}