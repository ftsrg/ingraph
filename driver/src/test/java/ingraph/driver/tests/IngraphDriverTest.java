package ingraph.driver.tests;

import ingraph.driver.CypherDriverFactory;
import ingraph.driver.data.IngraphDeltaHandler;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.driver.data.PrintDeltaHandler;
import ingraph.driver.ingraph.IngraphDriver;
import org.junit.Test;

public class IngraphDriverTest {

	@Test
	public void test() {
		try (IngraphDriver driver = CypherDriverFactory.createIngraphDriver()) {
			final IngraphQueryHandler handler = driver.session().registerQuery("q", "MATCH (n) RETURN n");
			IngraphDeltaHandler listener = new PrintDeltaHandler(handler.keys());
			handler.registerDeltaHandler(listener);
		}
	}

}
