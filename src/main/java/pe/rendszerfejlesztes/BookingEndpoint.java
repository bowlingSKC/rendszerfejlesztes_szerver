package pe.rendszerfejlesztes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.BookingServiceLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Az foglalások kezelését szolgáló végpont.
 * <p>
 *     A végpontot a következő linek lehet elérni: /api/booking
 * </p>
 */
@Path("booking")
public class BookingEndpoint {

    @EJB
    private BookingServiceLocal bookingService;


    /**
     * A paraméterben megadott felhasználó összes jegyét adja vissza.
     * @param user a felhasználó
     * @return a felhasználóhoz tartozó jegyek
     */
    @POST
    @Produces("application/json")
    public Response getTicketByUser(User user) {
        List<Ticket> tickets = bookingService.getUserTicket(user);
        GenericEntity<List<Ticket>> ticketsWrapper = new GenericEntity<List<Ticket>>(tickets) {};
        return Response.ok(ticketsWrapper).build();
    }

    /**
     * A megadott jegyet törli az adatbázisból.
     * @param ticket a törölni kívánt jegy
     * @return a törölt jegy
     */
    @POST
    @Produces("application/json")
    @Path("delete")
    public Response deleteTicket(Ticket ticket) {
        System.out.println(ticket);
        boolean state = bookingService.deleteTicket(ticket);
        if(state){
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }

    }

    /**
     * Megadott jegyhez visszaadja a hozzá tartozó szektort.
     * @param ticket a jegy
     * @return a jegyhez tartozó szektor
     */
    @POST
    @Produces("application/json")
    @Path("sectors")
    public Response getSectorByTicket(Ticket ticket) {
        Sector sector = bookingService.getSectorByTicket(ticket);
        return Response.ok(sector).build();
    }

    @PUT
    @Produces("application/json")
    @Path("book")
    public Response bookTicket(String ticket) {
        System.out.println(ticket);

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCdateAdapter()).create();
        Ticket obj = gson.fromJson(ticket, Ticket.class);
        Ticket booked = bookingService.bookTicket(obj);

        if( booked == null ) {
            return Response.serverError().build();
        }
        System.out.println("Sikeres jegy foglalas!");
        return Response.ok().build();
    }

}
