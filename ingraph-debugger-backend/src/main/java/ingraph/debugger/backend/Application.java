package ingraph.debugger.backend;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import ingraph.debugger.backend.managers.DriverManager;

@ApplicationPath("/")
public class Application extends ResourceConfig {

	public Application() {
		packages("ingraph.debugger.backend.handlers");
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new DriverManager()).to(DriverManager.class);
			}
		});
	}
}
