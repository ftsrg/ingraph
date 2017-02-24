package ingraph.debugger.backend.handlers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.managers.SessionManager;

@Path("api/deregister")
public class DeregisterQuery {

	@Inject
	SessionManager sms;
	
	@Inject
	DriverManager dms;
	
	@GET
	@Produces("text/plain")
	public String register() {
		return "Registered " + sms.hashCode();
	}
	
}
