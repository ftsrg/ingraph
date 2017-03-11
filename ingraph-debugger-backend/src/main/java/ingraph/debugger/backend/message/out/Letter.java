package ingraph.debugger.backend.message.out;

public class Letter {

	private String status;
	private Object body;

	public Letter() {
	}

	public Letter(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
