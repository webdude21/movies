package eu.webdude.movies.integration.route.imdb;

import eu.webdude.movies.integration.dto.imdb.PersonInfoDTO;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class NamesImportRoute extends ImdbImportRoute {

	@Override
	String getDownloadURI() {
		return BASE_DOWNLOAD_URI + "/name.basics.tsv.gz";
	}

	@Override
	String getDestination() {
		return "imdbNameImportProcessor";
	}

	@Override
	DataFormat getOutputFormat() {
		return new BindyCsvDataFormat(PersonInfoDTO.class);
	}
}
