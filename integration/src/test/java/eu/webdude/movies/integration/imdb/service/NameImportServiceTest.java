package eu.webdude.movies.integration.imdb.service;

import eu.webdude.movies.integration.imdbimport.dto.PersonInfoDTO;
import eu.webdude.movies.integration.imdbimport.service.NameImportService;
import eu.webdude.movies.model.Person;
import eu.webdude.movies.model.Profession;
import eu.webdude.movies.model.repository.PersonRepository;
import eu.webdude.movies.model.repository.ProfessionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NameImportServiceTest {

	@Spy
	private PersonRepository personRepository;

	@Spy
	private ProfessionRepository professionRepository;

	private NameImportService nameImportProcessor;

	private List<PersonInfoDTO> testPeople;

	private Profession testProfession = new Profession("actor");

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		nameImportProcessor = new NameImportService(personRepository, professionRepository);
		testPeople = getMockData(10);
	}

	private List<PersonInfoDTO> getMockData(int count) {
		return IntStream.range(0, count)
			.mapToObj(this::getMockPersonInfoDTO)
			.collect(Collectors.toList());
	}

	private PersonInfoDTO getMockPersonInfoDTO(int seed) {
		var person = new PersonInfoDTO();

		person.setBirthYear("194" + seed);
		person.setDeathYear("200" + seed);
		person.setKnownForTitles("Die Hard " + seed);
		person.setName("Bruce Willis");
		person.setNameRelation("s20123" + seed);
		person.setPrimaryProfession("actor");

		return person;
	}

	@Test
	public void testHandleImport() {
		when(professionRepository.save(testProfession)).thenReturn(testProfession);
		when(professionRepository.findByName(testProfession.getName())).thenReturn(Optional.of(testProfession));

		nameImportProcessor.handleImport(testPeople);

		verify(personRepository, only()).saveAll(anyList());

		verifyNoMoreInteractions(personRepository);
	}

	@Test
	public void testMapToPerson() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		var personInfoDTO = testPeople.get(0);
		var method = nameImportProcessor.getClass().getDeclaredMethod("mapToPerson", PersonInfoDTO.class);

		method.setAccessible(true);

		var resultPerson = (Person) method.invoke(nameImportProcessor, personInfoDTO);

		assertEquals(personInfoDTO.getName(), resultPerson.getFullName());
		assertEquals(personInfoDTO.getBirthYear(), resultPerson.getBirthYear());
		assertEquals(personInfoDTO.getDeathYear(), resultPerson.getDeathYear());
		assertEquals(personInfoDTO.getNameRelation(), resultPerson.getExternalKey());
	}

	@Test
	public void newPersonShouldBeCreatedIfSuchDoesNotExist() {
		when(professionRepository.save(testProfession)).thenReturn(testProfession);
		when(professionRepository.findByName(testProfession.getName())).thenReturn(Optional.empty());

		nameImportProcessor.handleImport(testPeople);

		verify(personRepository, only()).saveAll(anyList());
		verify(professionRepository, times(testPeople.size())).save(any(Profession.class));
		verify(professionRepository, times(testPeople.size())).findByName(testProfession.getName());

		verifyNoMoreInteractions(personRepository, professionRepository);
	}
}