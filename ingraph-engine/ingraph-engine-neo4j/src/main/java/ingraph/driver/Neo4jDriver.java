package ingraph.driver;

import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jDriver extends CypherDriver {

	final Driver driver;

	Neo4jDriver(final String uri, final AuthToken authToken) {
		driver = GraphDatabase.driver(uri, authToken);
	}

	@Override
	public boolean isEncrypted() {
		return driver.isEncrypted();
	}

	@Override
	public Session session() {
		return driver.session();
	}

	@Override
	public Session session(final AccessMode mode) {
		return driver.session(mode);
	}

	@Override
	public void close() {
		driver.close();
	}

}
