package ingraph.debugger.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import ingraph.debugger.backend.managers.DriverManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends ResourceConfig {

	public Application() {
		packages("ingraph.debugger.backend");
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new DriverManager()).to(DriverManager.class);
			}
		});
	}
}
