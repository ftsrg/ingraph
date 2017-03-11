package ingraph.debugger.backend.message.out;

import org.neo4j.cypher.SyntaxException;

public class RegisterParseErrorBody {

	private String message;
	
	public RegisterParseErrorBody(SyntaxException syntaxExc) {
		setMessage(syntaxExc.getMessage());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
