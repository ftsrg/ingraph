package ingraph.debugger.backend.handlers;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.messages.in.RegisterInMessage;
import ingraph.debugger.backend.messages.out.Letter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.UUID;

@Path("api/register")
public class RegisterQuery {

	@Inject
	DriverManager dms;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Letter registerPost(RegisterInMessage register) {
		UUID sessionID;

		if (register.getDefinition() == null) {
			throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
				.entity("Definition is null").type("text/plain").build());
		}

		return new Letter(Letter.Status.OK, "");
	}
}
