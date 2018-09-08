package eu.webdude.movies.web.rest;

import eu.webdude.movies.web.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/integration/import")
public class IntegrationImportController {

	private final IntegrationService service;

	@Autowired
	public IntegrationImportController(IntegrationService service) {
		this.service = service;
	}

	@RequestMapping(value = "/names", method = RequestMethod.POST)
	public ResponseEntity<?> importNames() {
		service.importData();
		return ResponseEntity.accepted().build();
	}
}
