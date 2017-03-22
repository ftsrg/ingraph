package ingraph.driver;

import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.Session;

public class IngraphDriver extends CypherDriver {

	IngraphDriver(final String uri, final AuthToken authToken) {
		super();
	}

	@Override
	public boolean isEncrypted() {
		return false;
	}

	@Override
	public Session session() {
		return null;
	}

	@Override
	public Session session(final AccessMode mode) {
		return null;
	}

	@Override
	public void close() {

	}

}
