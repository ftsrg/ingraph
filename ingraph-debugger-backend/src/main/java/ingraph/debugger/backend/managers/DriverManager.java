package ingraph.debugger.backend.managers;

import ingraph.driver.data.IngraphDeltaHandler;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.driver.ingraph.IngraphDriver;
import ingraph.driver.ingraph.IngraphSession;
import org.glassfish.jersey.media.sse.EventOutput;
import org.neo4j.driver.v1.Record;
import scala.collection.immutable.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class DriverManager {

	public class DeltaHandler extends IngraphDeltaHandler {
		private final Vector<String> keys;
		private final EventOutput eventOutput;

		public DeltaHandler(EventOutput eventOutput, Vector<String> keys) {
			this.eventOutput = eventOutput;
			this.keys = keys;
		}

		@Override
		public Vector<String> keys() {
			return keys;
		}

		@Override
		public void onChange(List<? extends Record> positiveRecords, List<? extends Record> negativeRecords) {
			System.out.println(positiveRecords.size() + " - " + negativeRecords.size());
		}
	}

	private IngraphSession session;

	private Map<UUID, IngraphQueryHandler> queryHandlerMap = new HashMap<>();

	public DriverManager() {
		IngraphDriver driver = new IngraphDriver();
		this.session = driver.session();
	}

	public UUID register(String definition) {
		UUID id = UUID.randomUUID();
		queryHandlerMap.put(id, session.registerQuery(id.toString(), definition));
		return id;
	}

	public List<String> getKeys(UUID id) {
		return queryHandlerMap.get(id).adapter().resultNamesJava();
	}

	public void registerHandler(UUID id, EventOutput eventOutput) {
		DeltaHandler handler = new DeltaHandler(eventOutput, queryHandlerMap.get(id).adapter().resultNames());
		queryHandlerMap.get(id).registerDeltaHandler(handler);
	}

}
