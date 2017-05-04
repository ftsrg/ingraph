package ingraph.driver.ingraph;

import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.Session;

import ingraph.driver.CypherDriver;

public class IngraphDriver extends CypherDriver {

	public IngraphDriver() {
		super();
	}

	@Override
	public boolean isEncrypted() {
		return false;
	}

	@Override
	public IngraphSession session() {
		return new IngraphSession();
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
