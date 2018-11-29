package eu.webdude.movies.integration.imdbimport.route;

import org.apache.camel.builder.RouteBuilder;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ImportJmsRoute extends RouteBuilder {

	public static final String IMPORT_ROUTE_PACKAGE = "eu.webdude.movies.integration.imdbimport.route";
	private final Class<ImdbImportRouteMetaInformation> routeMetaInfoClass = ImdbImportRouteMetaInformation.class;

	@Override
	public void configure() {
		from("jms:re-import-data")
			.log("Import command received")
			.process(exchange -> exchange.getIn().setBody(null))
			.multicast().parallelProcessing()
			.to(getImdbImportRoutes());
	}

	private String[] getImdbImportRoutes() {
		var reflections = new Reflections(IMPORT_ROUTE_PACKAGE);

		return reflections.getTypesAnnotatedWith(routeMetaInfoClass)
			.stream()
			.map(this::extractSourceComponent)
			.flatMap(Optional::stream)
			.toArray(String[]::new);
	}

	private Optional<String> extractSourceComponent(Class<?> importRoute) {
		return Stream.of(importRoute.getDeclaredAnnotationsByType(routeMetaInfoClass))
			.findAny()
			.map(ImdbImportRouteMetaInformation::sourceComponent);
	}
}
