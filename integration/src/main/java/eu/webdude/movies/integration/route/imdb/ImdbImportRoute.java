package eu.webdude.movies.integration.route.imdb;

import eu.webdude.movies.integration.aggregator.ArrayListAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;

public abstract class ImdbImportRoute extends RouteBuilder {

	static final String BASE_DOWNLOAD_URI = "http4://datasets.imdbws.com";

	abstract String getDownloadURI();

	abstract String getDestination();

	abstract DataFormat getOutputFormat();

	@Override
	public void configure() {
		var newLine = "\n";
		var bindyFormat = getOutputFormat();
		var aggregationStrategy = new ArrayListAggregationStrategy();

		from("timer:foo?period=5000")
			.log("Reimport data from IMDB")
			.to(getDownloadURI())
			.log("File downloaded")
			.unmarshal().gzip()
			.onCompletion()
			.split(body().tokenize(newLine)).streaming()
			.choice()
			.when(simple("${property.CamelSplitIndex} > 0"))
			.unmarshal(bindyFormat)
			.aggregate(constant(true), aggregationStrategy).completionSize(100).completionTimeout(2000)
			.bean(getDestination());
	}
}