package eu.webdude.movies.integration.imdbimport.route;

import eu.webdude.movies.integration.imdbimport.dto.PersonInfoDTO;
import eu.webdude.movies.model.repository.PersonRepository;
import org.apache.camel.Processor;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ImdbImportRouteMetaInformation(
	sourceComponent = "direct:import-people",
	downloadFileName = "name.basics.tsv.gz",
	destinationBean = "nameImportService"
)
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
	public DataFormat getOutputFormat() {
		return new BindyCsvDataFormat(PersonInfoDTO.class);
	}

	@Override
	Processor preImportHook() {
		return processor;
	}
}
