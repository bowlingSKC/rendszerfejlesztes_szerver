package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Location;
import pe.rendszerfejlesztes.services.EventServiceLocal;
import pe.rendszerfejlesztes.services.LocationServiceLocal;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("event")
public class EventService {

    @EJB
    private LocationServiceLocal locationService;
    @EJB
    private EventServiceLocal eventService;

    @GET
    @Path("locations")
    @Produces("application/json")
    public Response getAllLocations() {
        List<Location> locations = locationService.getAllLocation();
        GenericEntity<List<Location>> locationWrapper = new GenericEntity<List<Location>>(locations) {};
        return Response.ok(locationWrapper).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        GenericEntity<List<Event>> locationWrapper = new GenericEntity<List<Event>>(events) {};
        return Response.ok(locationWrapper).build();
    }

    @POST
    @Produces("application/json")
    @Path("search")
    public Response searchEvents(Event event) {
        List<Event> events = eventService.searchEvent(event);
        GenericEntity<List<Event>> eventsWrapper = new GenericEntity<List<Event>>(events) {};
        return Response.ok(eventsWrapper).build();
    }

}
