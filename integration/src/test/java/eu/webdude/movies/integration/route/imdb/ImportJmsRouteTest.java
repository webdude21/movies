package eu.webdude.movies.integration.route.imdb;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
@DisableJmx
public class ImportJmsRouteTest {

	@Autowired
	protected CamelContext camelContext;

	@Produce(uri = "jms:re-import-data")
	private ProducerTemplate template;

	@EndpointInject(uri = "mock:direct:import-data")
	private MockEndpoint directImportData;

	@Test
	public void configure() throws Exception {
		directImportData.expectedMessageCount(1);
		directImportData.expectedBodiesReceived((Object) null);

		template.sendBody("Test");

		directImportData.assertIsSatisfied();
	}
}