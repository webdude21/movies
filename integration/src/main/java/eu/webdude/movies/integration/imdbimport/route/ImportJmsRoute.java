package eu.webdude.movies.integration.imdbimport.route;

import org.apache.camel.builder.RouteBuilder;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class ImportJmsRoute extends RouteBuilder {

	@Override
	public void configure() {
		from("jms:re-import-data")
			.log("Import command received")
			.process(exchange -> exchange.getIn().setBody(null))
			.multicast().parallelProcessing()
			.to(getMulticastRoutes());
	}

	private String[] getMulticastRoutes() {
		var reflections = new Reflections("eu.webdude.movies.integration.imdbimport.route");

		return reflections.getTypesAnnotatedWith(ImdbImportRouteMetaInformation.class)
			.stream()
			.map(this::extractSourceComponent)
			.toArray(String[]::new);
	}

	private String extractSourceComponent(Class<?> importRoute) {
		return importRoute.getDeclaredAnnotationsByType(ImdbImportRouteMetaInformation.class)[0].sourceComponent();
	}
}
