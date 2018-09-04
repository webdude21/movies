package eu.webdude.movies.model.repository;

import eu.webdude.movies.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
