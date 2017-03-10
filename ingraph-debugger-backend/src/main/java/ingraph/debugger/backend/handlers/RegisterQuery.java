package ingraph.debugger.backend.handlers;

import java.net.HttpURLConnection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.neo4j.cypher.SyntaxException;
import org.neo4j.graphdb.QueryExecutionException;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.message.in.RegisterInMessage;
import ingraph.debugger.backend.message.out.Letter;
import ingraph.debugger.backend.message.out.RegisterOkBody;
import ingraph.debugger.backend.message.out.RegisterParseErrorBody;

@Path("api/register")
public class RegisterQuery {

	@Inject
	DriverManager dms;

	@GET
	@Produces("text/plain")
	public String registerGet() {
		return "Seems ok...";
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Letter registerPost(RegisterInMessage register) {
		String sessionID = "";

		if (register.getDefinition() == null) {
			throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("Definition is null").type("text/plain").build());
		}

		try {
			sessionID = dms.validate(register.getDefinition());
		} catch (QueryExecutionException e) {
			SyntaxException syntaxExc = (SyntaxException) e.getCause().getCause();
			Letter resp = new Letter("PARSE_ERROR");
			resp.setBody(new RegisterParseErrorBody(syntaxExc));
			return resp;
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build());
		}

		Letter resp = new Letter("OK");
		resp.setBody(new RegisterOkBody(sessionID));
		return resp;
	}
}
