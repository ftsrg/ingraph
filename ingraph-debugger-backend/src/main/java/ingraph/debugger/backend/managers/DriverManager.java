package ingraph.debugger.backend.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import neo4j.driver.reactive.interfaces.RecordChangeSetListener;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import neo4j.driver.reactive.impl.Neo4jReactiveDriver;
import neo4j.driver.reactive.interfaces.ReactiveDriver;
import neo4j.driver.reactive.interfaces.ReactiveSession;
import neo4j.driver.testkit.EmbeddedTestkitDriver;

public class DriverManager {

	ReactiveDriver driver;
	ReactiveSession session;

	Map<UUID, StatementResult> resultMap = new HashMap<>();

	public DriverManager() {
		this.driver = new Neo4jReactiveDriver(new EmbeddedTestkitDriver());
		this.session = driver.session();
	}

	public UUID register(String definition) {
		UUID id = UUID.randomUUID();

		try (Transaction t = session.beginTransaction()) {
			// = session.registerQuery(id.toString(), definition);

		}

		return id;
	}

	public List<String> columns(UUID id) {
		StatementResult statement = listenerMap.get(id);
		return statement.keys();

		return null;
	}

}
