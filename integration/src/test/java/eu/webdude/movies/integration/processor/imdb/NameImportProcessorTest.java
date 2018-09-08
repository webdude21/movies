package eu.webdude.movies.integration.processor.imdb;

import eu.webdude.movies.model.repository.PersonRepository;
import eu.webdude.movies.model.repository.ProfessionRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Spy;

public class NameImportProcessorTest {

	@Spy
	private PersonRepository personRepository;

	@Spy
	private ProfessionRepository professionRepository;

	private NameImportService nameImportProcessor;

	@Before
	public void setUp() {
		nameImportProcessor = new NameImportService(personRepository, professionRepository);
	}

	@Test
	@Ignore
	public void testHandleImport() {
	}
}