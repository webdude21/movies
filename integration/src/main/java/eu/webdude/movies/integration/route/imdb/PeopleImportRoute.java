package eu.webdude.movies.integration.route.imdb;

import eu.webdude.movies.integration.dto.imdb.PersonInfoDTO;
import eu.webdude.movies.model.repository.PersonRepository;
import org.apache.camel.Processor;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleImportRoute extends ImdbImportRoute {

	private final PersonRepository repository;

	@Autowired
	public PeopleImportRoute(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	String getDownloadFileName() {
		return "name.basics.tsv.gz";
	}

	@Override
	String getSourceComponent() {
		return "direct:import-data";
	}

	@Override
	String getDestinationBean() {
		return "imdbNameImportProcessor";
	}

	@Override
	DataFormat getOutputFormat() {
		return new BindyCsvDataFormat(PersonInfoDTO.class);
	}

	@Override
	Processor preImportHook() {
		return exchange -> repository.deleteAll();
	}
}
