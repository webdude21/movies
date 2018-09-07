package eu.webdude.movies.integration.route.imdb;

import eu.webdude.movies.integration.dto.imdb.PersonInfoDTO;
import eu.webdude.movies.model.repository.PersonRepository;
import org.apache.camel.Processor;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleImportRoute extends ImdbImportRoute {

	private Logger logger = LoggerFactory.getLogger(PeopleImportRoute.class);

	private final Processor processor;

	@Autowired
	public PeopleImportRoute(PersonRepository repository) {
		processor = exchange -> {
			logger.info("Deleted all of the people previously added in the database.");
			repository.deleteAll();
		};
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
		return processor;
	}
}
