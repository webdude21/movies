package eu.webdude.movies.integration.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;

public class ArrayListAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		Message newIn = newExchange.getIn();
		Object newBody = newIn.getBody();
		ArrayList list;

		if (oldExchange == null) {
			list = new ArrayList<>();
			list.add(newBody);
			newIn.setBody(list);
			return newExchange;
		} else {
			Message in = oldExchange.getIn();
			list = in.getBody(ArrayList.class);
			list.add(newBody);
			return oldExchange;
		}
	}
}