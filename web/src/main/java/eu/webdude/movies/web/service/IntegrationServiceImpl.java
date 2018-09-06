package eu.webdude.movies.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class IntegrationServiceImpl implements IntegrationService {

	private final JmsTemplate jmsTemplate;

	private static final Logger logger = LoggerFactory.getLogger(IntegrationServiceImpl.class);

	@Autowired
	public IntegrationServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void importData() {
		logger.debug("Sending the import data start command!");
		jmsTemplate.convertAndSend("");
	}
}
