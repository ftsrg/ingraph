package ingraph.debugger.backend;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import ingraph.debugger.backend.managers.DriverManager;
import ingraph.debugger.backend.managers.SessionManager;

@ApplicationPath("/")
public class BackendApplication extends ResourceConfig {

	public BackendApplication() {	
		packages("ingraph.debugger.backend.handlers");
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new SessionManager()).to(SessionManager.class);
				bind(new DriverManager()).to(DriverManager.class);
			}
		});
	}
}
