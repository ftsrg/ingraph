package ingraph.debugger.backend.message.out;

public class RegisterOkBody {
	
	private String sessionId;

	public RegisterOkBody(String sessionId) {
		super();
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
