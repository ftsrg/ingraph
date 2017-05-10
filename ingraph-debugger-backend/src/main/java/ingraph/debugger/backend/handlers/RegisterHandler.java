package ingraph.debugger.backend.handlers;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.messages.in.RegisterInMessage;
import ingraph.debugger.backend.messages.out.Letter;
import ingraph.debugger.backend.messages.out.RegisterOkBody;
import ingraph.driver.data.Repackager;
import org.neo4j.driver.v1.Record;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

@Path("api/register")
public class RegisterHandler {

	@Inject
	DriverManager dms;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Letter registerPost(RegisterInMessage register) {
		try {
			if (register.getDefinition() == null) {
				throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("Definition is null").type("text/plain").build());
			}

			UUID id = dms.register(register.getDefinition());
			List<String> keys = dms.getKeys(id);
			List<? extends Record> results = dms.getResults(id);

			return new Letter(Letter.Status.OK, new RegisterOkBody(id, keys, results));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException();
		}
	}

}
