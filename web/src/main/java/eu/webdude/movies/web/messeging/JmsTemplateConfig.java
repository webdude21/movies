package eu.webdude.movies.web.messeging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsTemplateConfig {

	private static final String QUEUE = "re-import-data";

	private final ActiveMQConnectionFactory connectionFactory;

	@Autowired
		public JmsTemplateConfig(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory);
		connectionFactory.setTrustAllPackages(true);
		template.setDefaultDestinationName(QUEUE);
		return template;
	}
}
