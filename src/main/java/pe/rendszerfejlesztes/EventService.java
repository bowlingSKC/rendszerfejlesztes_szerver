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

/**
 * Az események és helyszínek kezelését szolgáló végpont.
 * <p>
 *     A végpontot a következő linek lehet elérni: /api/event
 * </p>
 */
@Path("event")
public class EventService {

    /**
     * A helyszínek kezelését szolgáló EJB.
     * @see pe.rendszerfejlesztes.services.EventServiceLocal
     */
    @EJB
    private LocationServiceLocal locationService;

    /**
     * Az események kezelését szolgáló EJB.
     * @see pe.rendszerfejlesztes.services.EventServiceLocal
     */
    @EJB
    private EventServiceLocal eventService;

    /**
     * Az adatbázisban található összes helyeszín listáját adja vissza JSON formátumban.
     * <p>
     *     Elérése GET hívással: /api/event/locations
     * </p>
     * @return az összes helyszín listjája
     */
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

}
