package eu.webdude.movies.integration.service.imdb;

import eu.webdude.movies.integration.dto.imdb.PersonInfoDTO;
import eu.webdude.movies.model.Person;
import eu.webdude.movies.model.Profession;
import eu.webdude.movies.model.repository.PersonRepository;
import eu.webdude.movies.model.repository.ProfessionRepository;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("nameImportService")
public class NameImportService {

	private PersonRepository personRepository;

	private ProfessionRepository professionRepository;

	@Autowired
	public NameImportService(PersonRepository personRepository, ProfessionRepository professionRepository) {
		this.personRepository = personRepository;
		this.professionRepository = professionRepository;
	}

	@Handler
	@Transactional
	public void handleImport(List<PersonInfoDTO> peopleInfoImportDTO) {
		var peopleToSave = peopleInfoImportDTO.stream()
			.map(this::mapToPerson)
			.collect(Collectors.toList());

		personRepository.saveAll(peopleToSave);
	}

	private Person mapToPerson(PersonInfoDTO personInfoDTO) {
		final var person = new Person();

		person.setFullName(personInfoDTO.getName());
		person.setBirthYear(personInfoDTO.getBirthYear());
		person.setDeathYear(personInfoDTO.getDeathYear());
		person.setExternalKey(personInfoDTO.getNameRelation());

		Arrays.stream(personInfoDTO.getPrimaryProfessions())
			.map(this::mapProfession)
			.forEach(person::addProfession);

		person.setSyncDate(LocalDateTime.now());

		return person;
	}

	private Profession mapProfession(String professionName) {
		return professionRepository
			.findByName(professionName)
			.orElseGet(() -> professionRepository.save(new Profession(professionName)));
	}
}
