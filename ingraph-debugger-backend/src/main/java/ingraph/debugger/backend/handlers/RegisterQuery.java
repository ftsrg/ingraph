package ingraph.debugger.backend.handlers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.managers.SessionManager;

@Path("api/register")
public class RegisterQuery {

	@Inject
	SessionManager sms;
	
	@Inject
	DriverManager dms;
	
	@GET
	@Produces("text/plain")
	public String registerGet() {
		return "Registered " + sms.hashCode();
	}
	
	@POST
	@Produces("application/json")
	public Object registerPost() {
		return new Object();
	}
}
