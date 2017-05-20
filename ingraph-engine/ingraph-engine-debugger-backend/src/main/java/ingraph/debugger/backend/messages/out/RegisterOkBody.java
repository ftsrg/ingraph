package ingraph.debugger.backend.messages.out;

import java.util.List;
import java.util.UUID;

import neo4j.driver.reactive.data.RecordChangeSet;

public class RegisterOkBody {

	private String sessionId;
	private List<String> columns;
	private RecordChangeSet initialMatches;

	public RegisterOkBody(UUID sessionId, List<String> columns, RecordChangeSet initial) {
		this.sessionId = sessionId.toString();
		this.columns = columns;
		this.initialMatches = initial;
	}

	public String getSessionId() {
		return sessionId;
	}

	public List<String> getColumns() {
		return columns;
	}

	public RecordChangeSet getInitialData() {
		return initialMatches;
	}

}
