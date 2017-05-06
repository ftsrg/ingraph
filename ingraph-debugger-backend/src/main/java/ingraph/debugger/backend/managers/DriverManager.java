package ingraph.debugger.backend.managers;

import ingraph.driver.ingraph.IngraphDriver;
import org.neo4j.driver.v1.Transaction;

import java.util.List;
import java.util.UUID;

public class DriverManager {

	IngraphDriver driver;

	public DriverManager() {
		this.driver = new IngraphDriver();
		this.session = driver.session();
	}

	public UUID register(String definition) {
		UUID id = UUID.randomUUID();

		try (Transaction t = session.beginTransaction()) {
			t
		}

		return id;
	}

	public List<String> columns(UUID id) {
//		StatementResult statement = listenerMap.get(id);
//		return statement.keys();

		return null;
	}

}
