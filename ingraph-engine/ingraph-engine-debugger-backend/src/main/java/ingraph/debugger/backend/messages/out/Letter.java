package ingraph.debugger.backend.messages.out;

public class Letter {

	public enum Status {
		OK, PARSE_ERROR
	}

	private String status;
	private Object body;

	public Letter(Status status, Object body) {
		this.status = status.name();
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public Object getBody() {
		return body;
	}

}
