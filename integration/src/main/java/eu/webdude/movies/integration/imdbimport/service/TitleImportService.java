package eu.webdude.movies.integration.imdbimport.service;

import eu.webdude.movies.integration.imdbimport.dto.TitleDTO;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("titleImportService")
public class TitleImportService {

	@Handler
	@Transactional
	public void handleImport(List<TitleDTO> titleDTOList) {
		//TODO Implement import logic
		titleDTOList.forEach(System.out::println);
	}
}
