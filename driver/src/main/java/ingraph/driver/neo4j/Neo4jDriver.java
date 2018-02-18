package ingraph.driver.neo4j;

import ingraph.driver.CypherDriver;
import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.test.TestGraphDatabaseFactory;

public class Neo4jDriver extends CypherDriver {

	final Driver driver;

	public Neo4jDriver(final String uri, final AuthToken authToken) {
		GraphDatabaseSettings.BoltConnector bolt = GraphDatabaseSettings.boltConnector("0");

		String host = "localhost";
		int port = 7687; // this is the default Neo4j port - we start one higher than this to avoid collisions
		GraphDatabaseService graphDb = null;
		String address = null;
		while (port < 65000) {
			port++;
			address = String.format("%s:%d", host, port);
			System.out.println("instantiating database listening on " + address);
			try {
				graphDb = new TestGraphDatabaseFactory()
					.newImpermanentDatabaseBuilder()
					.setConfig(bolt.type, "BOLT")
					.setConfig(bolt.enabled, "true")
					.setConfig(bolt.address, address)
					.newGraphDatabase();
			} catch (RuntimeException e) {
				// this is usually a org.neo4j.kernel.lifecycle.LifecycleException
				// caused by org.neo4j.helpers.PortBindException
				e.printStackTrace();
				System.out.println("Cannot connect on port " + port + ", retrying on a higher port.");
				continue;
			}
			break;
		}
		System.out.println(graphDb);

		driver = GraphDatabase.driver("bolt://" + address);
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
	public Session session(Iterable<String> bookmarks) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(AccessMode mode, Iterable<String> bookmarks) {
		return null;
	}

	@Override
	public void close() {
		driver.close();
	}

}
