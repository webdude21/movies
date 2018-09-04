package eu.webdude.movies.integration.processor.imdb;

import eu.webdude.movies.integration.dto.imdb.PersonInfoDTO;
import eu.webdude.movies.model.Person;
import eu.webdude.movies.model.repository.PersonRepository;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component("imdbNameImportProcessor")
public class NameImportProcessor {

	private PersonRepository personRepository;

	@Autowired
	public NameImportProcessor(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Handler
	@Transactional
	public void handleImport(ArrayList<PersonInfoDTO> peopleInfoImportDTO) {
		var peopleToSave = peopleInfoImportDTO.stream()
			.map(NameImportProcessor::mapToPerson)
			.collect(Collectors.toList());

		personRepository.saveAll(peopleToSave);
	}

	private static Person mapToPerson(PersonInfoDTO personInfoDTO) {
		final var person = new Person();

		person.setFullName(personInfoDTO.getName());
		person.setBirthYear(personInfoDTO.getBirthYear());
		person.setDeathYear(personInfoDTO.getDeathYear());
		person.setImportForeignKey(personInfoDTO.getNameRelation());
		person.setProfession(personInfoDTO.getPrimaryProfession());

		return person;
	}
}
