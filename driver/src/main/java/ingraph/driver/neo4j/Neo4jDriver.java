package ingraph.driver.neo4j;

import ingraph.driver.CypherDriver;
import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jDriver extends CypherDriver {

	final Driver driver;

	public Neo4jDriver(final String uri, final AuthToken authToken) {
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
	public Session session(final String bookmark) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(final AccessMode mode, final String bookmark) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public void close() {
		driver.close();
	}

}
