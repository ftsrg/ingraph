package ingraph.driver.tests;

import ingraph.driver.CypherDriverFactory;
import org.junit.Test;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

public class IngraphDriverTest {

	@Test
	public void test() throws Exception {
		try (Driver driver = CypherDriverFactory.createIngraphDriver()) {
			final Session session = driver.session();
			final Transaction transaction = session.beginTransaction();
			final StatementResult result = transaction.run("MATCH (n) RETURN n LIMIT 5");
			while (result.hasNext()) {
				final Record record = result.next();
				System.out.println(record);
			}
		}
	}

}
