package ingraph.debugger.backend.handlers;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ingraph.debugger.backend.managers.DriverManager;

@Path("api/deregister")
public class DeregisterQuery {

	@POST
	@Produces("text/plain")
	public String deregister() {
		return null;
	}

}
