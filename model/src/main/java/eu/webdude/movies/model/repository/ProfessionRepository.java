package eu.webdude.movies.model.repository;

import eu.webdude.movies.model.Profession;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfessionRepository extends PagingAndSortingRepository<Profession, Long> {

	Optional<Profession> findByName(String name);
}
