package eu.webdude.movies.integration.route.imdb;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ImportJmsRoute extends RouteBuilder {

	@Override
	public void configure() {
		from("jms:re-import-data")
			.log("Import command received")
			.process(exchange -> exchange.getIn().setBody(null))
			.to("direct:import-data");
	}
}
