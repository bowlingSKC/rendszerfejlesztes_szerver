package pe.rendszerfejlesztes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pe.rendszerfejlesztes.database.EventConnector;
import pe.rendszerfejlesztes.database.PerformerConnector;
import pe.rendszerfejlesztes.database.impl.PerformerConnectorImpl;
import pe.rendszerfejlesztes.modell.*;
import pe.rendszerfejlesztes.services.BookService;
import pe.rendszerfejlesztes.services.EventService;
import pe.rendszerfejlesztes.services.LocationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.lang.annotation.Retention;
import java.util.Date;
import java.util.List;

/**
 * Az események és helyszínek kezelését szolgáló végpont.
 * <p>
 *     A végpontot a következő linek lehet elérni: /api/event
 * </p>
 */
@Path("event")
public class EventEndpoint {

    private EventService eventService = new EventService();
    private LocationService locationService = new LocationService();
    private BookService bookService = new BookService();
    private PerformerConnector performerConnector = new PerformerConnectorImpl();

    @GET
    @Path("locations")
    @Produces("application/json")
    public Response getAllLocations() {
        System.out.println( "Keres erkezett: osszes helyszin lekerdezese" );
        List<Location> locations = locationService.getAllLocation();
        GenericEntity<List<Location>> locationWrapper = new GenericEntity<List<Location>>(locations) {};
        return Response.ok(locationWrapper).build();
    }

    /**
     * Az adatbázisban található összes esemény listáját adja vissza JSON formátumban.
     * <p>
     *     Elérése GET hívással: /api/event
     * </p>
     * @return az összes esemény listája
     */
    @GET
    @Produces("application/json")
    public Response getAllEvents() {
        System.out.println( "Keres erkezett: osszes esemeny lekerdezese" );
        //List<Event> events = eventService.getAllEvents();
        List<Event> events = eventService.getAllEvents();
        GenericEntity<List<Event>> locationWrapper = new GenericEntity<List<Event>>(events) {};
        return Response.ok(locationWrapper).build();
    }

    /**
     * A paraméterben megadott objektum adattagjainak értéke alapján keres a programok között.
     * Üres listát ad vissza, ha egy programot sem talál.
     * @param event keresési feltételek
     * @return a keresési feltételeknek megfelelő események listája
     */
    @POST
    @Produces("application/json")
    @Path("search")
    public Response searchEvents(Event event) {
        System.out.println( "Keres erkezett: esemenyek keresese, keresesi feltetel: " + event );
        List<Event> events = eventService.searchEvent(event);
        GenericEntity<List<Event>> eventsWrapper = new GenericEntity<List<Event>>(events) {};
        return Response.ok(eventsWrapper).build();
    }

    @GET
    @Produces("application/json")
    @Path("sectors/byevent/{id}")
    public Response getSectorsByEventId(@PathParam("id") Integer id) {
        List<Sector> sectors = eventService.getSectorsByEventId(id);
        GenericEntity<List<Sector>> sectorsWrapper = new GenericEntity<List<Sector>>(sectors) {};
        return Response.ok(sectorsWrapper).build();
    }


    @POST
    @Produces("application/json")
    @Path("sectors/bysector")
    public Response getEventBySector(Sector sector) {
        Event event = eventService.getEventBySector(sector);
        System.out.println("A keresett esemeny szektor szerint: " + event);
        return Response.ok(event).build();
    }

    @POST
    @Produces("application/json")
    @Path("sectors/byticket")
    public Response getSectorByTicket(String ticket) {
        System.out.println( ticket );
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCdateAdapter()).create();
        Ticket obj = gson.fromJson(ticket, Ticket.class);
        Sector sector = bookService.getSectorByTicket(obj);
        return Response.ok(sector).build();
    }

    @GET
    @Produces("application/json")
    @Path("performers")
    public Response getAllPerformer() {
        List<Performer> performers = performerConnector.getAllPerformer();
        GenericEntity<List<Performer>> eventsWrapper = new GenericEntity<List<Performer>>(performers) {};
        return Response.ok(eventsWrapper).build();
    }

    @POST
    @Produces("application/json")
    @Path("performers")
    public Response createNewPerformer(String performer) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCdateAdapter()).create();
        Performer obj = gson.fromJson(performer, Performer.class);
        Performer created = performerConnector.createPerformer(obj);
        return Response.ok(created).build();
    }

    @PUT
    @Produces("application/json")
    public Response createNewEvent(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCdateAdapter()).create();
        Event obj = gson.fromJson(json, Event.class);
        eventService.createNewEvent(obj);
        return Response.ok(obj).build();
    }

}
