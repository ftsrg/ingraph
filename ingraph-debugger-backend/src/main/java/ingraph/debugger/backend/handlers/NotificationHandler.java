package ingraph.debugger.backend.handlers;

import ingraph.debugger.backend.managers.DriverManager;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.UUID;

@Path("api/notification")
public class NotificationHandler {

	@Inject
	DriverManager dms;

	@GET
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput getNotifications(@QueryParam("id") String id) {
		final EventOutput eventOutput = new EventOutput();
		dms.registerHandler(UUID.fromString(id), eventOutput);
		return eventOutput;
	}

}
