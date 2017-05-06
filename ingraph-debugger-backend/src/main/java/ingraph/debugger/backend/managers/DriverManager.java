package ingraph.debugger.backend.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ingraph.driver.IngraphDriver;
import neo4j.driver.reactive.interfaces.RecordChangeSetListener;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import neo4j.driver.reactive.impl.Neo4jReactiveDriver;
import neo4j.driver.reactive.interfaces.ReactiveDriver;
import neo4j.driver.reactive.interfaces.ReactiveSession;
import neo4j.driver.testkit.EmbeddedTestkitDriver;

public class DriverManager {

	IngraphDriver driver;

	public DriverManager() {
		this.driver = new IngraphDriver();
		this.session = driver.session();
	}

	public UUID register(String definition) {
		UUID id = UUID.randomUUID();

		try (Transaction t = session.beginTransaction()) {
			t.
		}

		return id;
	}

	public List<String> columns(UUID id) {
		StatementResult statement = listenerMap.get(id);
		return statement.keys();

		return null;
	}

}
