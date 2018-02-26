package ingraph.driver.tests;

import ingraph.driver.CypherDriverFactory;
import ingraph.driver.data.IngraphDeltaHandler;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.driver.data.PrintDeltaHandler;
import ingraph.driver.ingraph.IngraphDriver;
import ingraph.driver.ingraph.IngraphSession;
import org.junit.Test;

public class IngraphDriverTest {

	@Test
	public void changeListenerTest() {
		try (IngraphDriver driver = CypherDriverFactory.createIngraphDriver()) {
			// a single session uses the same indexer
			final IngraphSession session = driver.session();

			final IngraphQueryHandler readHandler = session.registerQuery("read", "MATCH (n:Person) RETURN n.name");
			IngraphDeltaHandler listener = new PrintDeltaHandler(readHandler.keys());
			readHandler.registerDeltaHandler(listener);

			System.out.println("First create");
			session.run("CREATE (n:Person {name: 'Jane'})");
			readHandler.adapter().result();

			System.out.println("Second create");
			session.run("CREATE (n:Person {name: 'Jake'})");
			readHandler.adapter().result();
		}
	}

}
