package ingraph.driver.tests;
import static org.neo4j.driver.v1.Values.parameters;

import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import ingraph.driver.CypherDriverFactory;

public class Neo4jDriverTest {

	@Test
	public void test() throws Exception {
		try (Driver driver = CypherDriverFactory.createNeo4jDriver("bolt://localhost:7687",
				AuthTokens.basic("neo4j", "admin"))) {
			try (Session session = driver.session()) {
				try (Transaction tx = session.beginTransaction()) {
					tx.run("CREATE (a:Person {name: $name, title: $title})",
							parameters("name", "Arthur", "title", "King"));
					tx.success();
				}

				try (Transaction tx = session.beginTransaction()) {
					StatementResult result = tx.run( //
							"MATCH (a:Person) WHERE a.name = $name " + //
									"RETURN a.name AS name, a.title AS title", //
							parameters("name", "Arthur"));
					while (result.hasNext()) {
						Record record = result.next();
						System.out.println(
								String.format("%s %s", record.get("title").asString(), record.get("name").asString()));
					}
				}
			}
		}
	}
}
