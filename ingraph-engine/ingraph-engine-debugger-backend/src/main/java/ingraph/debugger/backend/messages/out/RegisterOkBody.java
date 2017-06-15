package ingraph.debugger.backend.messages.out;

import org.neo4j.driver.v1.Record;

import java.util.List;
import java.util.UUID;

public class RegisterOkBody {

	private String sessionId;
	private List<String> columns;
	private List<? extends Record> initialResults;

	public RegisterOkBody(UUID sessionId, List<String> columns, List<? extends Record> initialResults) {
		this.sessionId = sessionId.toString();
		this.columns = columns;
		this.initialResults = initialResults;
	}

	public String getSessionId() {
		return sessionId;
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<? extends Record> getInitialResults() {
		return initialResults;
	}
}
