package eu.webdude.movies.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServiceImpl implements IntegrationService {

	private final JmsTemplate jmsTemplate;

	@Autowired
	public IntegrationServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void importData() {
		jmsTemplate.convertAndSend("");
	}
}
