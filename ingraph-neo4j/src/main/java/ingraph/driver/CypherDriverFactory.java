package ingraph.driver;

import org.neo4j.driver.v1.AuthToken;

public class CypherDriverFactory {

	public static CypherDriver createNeo4jDriver(String uri, AuthToken authToken) {
		return new Neo4jDriver(uri, authToken);
	}

	public static IngraphDriver createIngraphDriver(String uri, AuthToken authToken) {
		return new IngraphDriver(uri, authToken);
	}

}
