package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.modell.wrappers.BookingWrapper;
import pe.rendszerfejlesztes.services.BookingServiceLocal;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class BookingService {

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
    /*@POST
    @Produces("application/json")
    @Path("delete")
    public Response deleteTicket(Ticket ticket) {
        if(bookingService.deleteTicket(ticket)){
            System.out.println("Removed: " + ticket.toString());
            return Response.ok(ticket).build();

        }else{
            System.out.println("Failed to remove: " + ticket.toString());
            return Response.status(400).build();
        }

    }*/
    @DELETE
    @Produces("application/json")
    @Path("{ticket}")
    public Response deleteTicket(@PathParam("ticket") int ticketID) {
        if(bookingService.deleteTicket(ticketID)){
            System.out.println("Removed: " + ticketID);
            return Response.ok().build();

        }else{
            System.out.println("Failed to remove: " + ticketID);
            return Response.status(400).build();
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
    @Path("book/{user}/{sector}")
    public Response bookTicket(@PathParam("user") int userId, @PathParam("sector") int sectorId) {
        Ticket ticket = new Ticket();
        ticket.setUser(new User(userId));
        ticket.setStatus(2);
        ticket.setSector(new Sector(sectorId));
        ticket.setPaid(false);
        ticket.setBookedTime(new Date());

        Ticket booked = bookingService.bookTicket(ticket);
        if( booked == null ) {
            return Response.serverError().build();
        }
        System.out.println("Sikeres jegy foglalas!");
        return Response.ok(booked).build();
    }

}
