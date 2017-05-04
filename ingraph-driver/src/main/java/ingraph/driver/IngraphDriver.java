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
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(final AccessMode mode) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(final String bookmark) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(final AccessMode mode, String bookmark) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public void close() {

	}

}
