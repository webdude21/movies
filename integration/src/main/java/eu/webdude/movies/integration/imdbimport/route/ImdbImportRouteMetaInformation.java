package eu.webdude.movies.integration.imdbimport.route;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImdbImportRouteMetaInformation {
	String sourceComponent();

	String downloadFileName();

	String destinationBean();
}
