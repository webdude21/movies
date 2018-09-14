package eu.webdude.movies.integration.imdbimport.route;

import eu.webdude.movies.integration.aggregator.ArrayListAggregationStrategy;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ImdbImportRoute extends RouteBuilder {

	private Logger logger = LoggerFactory.getLogger(ImportJmsRoute.class);

	private static final String BASE_DOWNLOAD_URI = "http4://datasets.imdbws.com/";

	abstract String getDownloadFileName();

	abstract String getSourceComponent();

	abstract String getDestinationBean();

	abstract DataFormat getOutputFormat();

	Processor preImportHook() {
		return exchange -> logger.info("Running pre import hook!");
	}

	@Override
	public void configure() {
		var newLine = "\n";
		var bindyFormat = getOutputFormat();
		var aggregationStrategy = new ArrayListAggregationStrategy();
		var downloadFileName = getDownloadFileName();
		var isNotHeader = simple("${property.CamelSplitIndex} > 0");

		from(getSourceComponent())
			.log("Import data from IMDB. Using file " + downloadFileName)
			.to(BASE_DOWNLOAD_URI + downloadFileName)
			.log("File " + downloadFileName + " downloaded")
			.process(preImportHook())
			.unmarshal().gzip()
			.onCompletion()
			.split(body().tokenize(newLine)).streaming()
			.choice()
			.when(isNotHeader)
			.unmarshal(bindyFormat)
			.aggregate(constant(true), aggregationStrategy).completionSize(1000).completionTimeout(2000)
			.bean(getDestinationBean());
	}
}