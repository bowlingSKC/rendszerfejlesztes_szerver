package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.*;
import pe.rendszerfejlesztes.database.impl.DiscountConnectorImpl;
import pe.rendszerfejlesztes.database.impl.SectorConnectorImpl;
import pe.rendszerfejlesztes.database.impl.TicetConnectorImpl;
import pe.rendszerfejlesztes.modell.Discount;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import java.util.List;

public class BookService {

    private TicketConnector ticketConnector = new TicetConnectorImpl();
    private DiscountConnector discountConnector = new DiscountConnectorImpl();
    private SectorConnector sectorConnector = new SectorConnectorImpl();

    public List<Ticket> getUserTicket(User user) {
        List<Ticket> tickets = ticketConnector.getTicketsByUserId(user.getId());
        return tickets;
    }

    public Ticket bookTicket(Ticket ticket) {
        if( ticket.getRow() == null|| ticket.getCol() == null ) {
            return bookNonSeatedTicket(ticket);
        } else {
            return bookSeatedTicket(ticket);
        }
    }

    private Ticket bookNonSeatedTicket(Ticket ticket) {
        Sector sector = sectorConnector.getSectorById(ticket.getSector().getId());
        if( sector == null ) {
            return null;
        }

        if( sector.isFull() ) {
            return null;
        }

        ticketConnector.bookTicket(ticket);
        return ticket;
    }

    private Ticket bookSeatedTicket(Ticket ticket) {
        Sector sector = sectorConnector.getSectorById(ticket.getSector().getId());
        if( sector == null ) {
            return null;
        }

        if( sector.isSeatReserved(ticket.getCol(), ticket.getRow()) ) {
            return null;
        }

        ticketConnector.bookTicket(ticket);
        return ticket;
    }

    public boolean deleteTicket(Ticket ticket) {
        ticketConnector.deleteTicket(ticket);
        return true;
    }

    public List<Ticket> getAllTicket() {
        List<Ticket> tickets = ticketConnector.getAllTicket();
        return tickets;
    }

    /**
     * Adatbázisból kikeresi a jegyhez tartozó szektort.
     * @param ticket jegy
     * @return a jegyhez tartozó szektor
     */
    public Sector getSectorByTicket(Ticket ticket) {
        List<Ticket> tickets = getAllTicket();
        for(Ticket t : tickets) {
            if( t.getId().equals(ticket.getId()) ) {
                return t.getSector();
            }
        }
        return null;
    }

    public boolean updateDiscount(Discount discount, Integer id){
        return discountConnector.updateDiscount(discount,id);
        /*Ticket found = (Ticket)em.createQuery("SELECT t FROM Ticket t WHERE t.id LIKE :ticid").setParameter("ticid",id).getSingleResult();
        Ticket old = em.find(Ticket.class, id);
        if(old != null){
            System.out.println(discount);
            System.out.println(old);
            //em.remove(old);
            old.setDiscount(discount);
            //em.persist(old);
            em.merge(old);
            em.flush();
            return true;
        }

        return false;*/
    }

    public List<Discount> getAllDiscount(){
        return discountConnector.getAllDiscount();
        /*List<Discount> discounts = em.createQuery("SELECT d FROM Discount d").getResultList();
        return discounts;*/
    }
}
