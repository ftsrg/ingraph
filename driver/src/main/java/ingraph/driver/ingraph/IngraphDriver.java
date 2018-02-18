package ingraph.driver.ingraph;

import ingraph.driver.CypherDriver;
import org.neo4j.driver.v1.AccessMode;
import org.neo4j.driver.v1.Session;

/**
 * Current limitations include:
 *
 * <ul>
 *     <li>Queries with updates (transformations) cannot be registered, i.e. {@code MATCH (n) CREATE (n)-[:REL]->()} is not supported.</li>
 *     <li>Queries cannot return nodes or relationships, only values, i.e. {@code MATCH (n)-[e]->() RETURN n, e} does not work, but
 *     {@code MATCH (n)-[e]->() RETURN n.name, e.weight} does.</li>
 * </ul>
 */
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
	public Session session(Iterable<String> bookmarks) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public Session session(AccessMode mode, Iterable<String> bookmarks) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public void close() {
	}

}
