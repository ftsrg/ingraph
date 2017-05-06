package ingraph.driver;

import org.neo4j.driver.v1.AuthToken;

import ingraph.driver.ingraph.IngraphDriver;
import ingraph.driver.neo4j.Neo4jDriver;

public class CypherDriverFactory {

	public static CypherDriver createNeo4jDriver(String uri, AuthToken authToken) {
		return new Neo4jDriver(uri, authToken);
	}

	public static IngraphDriver createIngraphDriver() {
		return new IngraphDriver();
	}

}
