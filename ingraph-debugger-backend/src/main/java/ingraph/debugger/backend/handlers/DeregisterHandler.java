package ingraph.debugger.backend.handlers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("api/deregister")
public class DeregisterHandler {

	@POST
	@Produces("text/plain")
	public String deregister() {
		return null;
	}

}
