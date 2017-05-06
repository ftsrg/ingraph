package ingraph.debugger.backend.messages.out;

import java.util.List;
import java.util.UUID;

public class RegisterOkBody {

	private String sessionId;
	private List<String> columns;

	public RegisterOkBody(UUID sessionId, List<String> columns) {
		this.sessionId = sessionId.toString();
		this.columns = columns;
	}

	public String getSessionId() {
		return sessionId;
	}

	public List<String> getColumns() {
		return columns;
	}
}
