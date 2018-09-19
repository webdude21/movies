package eu.webdude.movies.integration.imdbimport.route;

import eu.webdude.movies.integration.imdbimport.dto.TitleDTO;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
@ImdbImportRouteMetaInformation(
	sourceComponent = "direct:import-titles",
	downloadFileName = "title.akas.tsv.gz",
	destinationBean = "titleImportService"
)
public class TitleImportRoute extends ImdbImportRoute {

	@Override
	public DataFormat getOutputFormat() {
		return new BindyCsvDataFormat(TitleDTO.class);
	}
}
