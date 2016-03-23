package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.BookingService;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link pe.rendszerfejlesztes.services.BookingServiceLocal} egy implementált osztálya relációs adatbázisok perzisztens rétegének megvalósításához.
 * Az osztály egyben egy állapot nélküli EJB is.
 * @see pe.rendszerfejlesztes.modell.Ticket
 */
@Stateless
public class BookService implements BookingServiceLocal {

    /**
     * Adatbázist megvalósító osztály.
     * Ezen adattagon kereszül kell az adatbázis-műveleteket megvalósítani.
     * <p>
     *     A PersistenceContex annotáció unitName mezőjének az értéke a persistence.xml fájlban van definiálva.
     * </p>
     */
    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    /**
     * A paraméterben kapott felhasználó összes foglalt jegyeit keresi meg az adatbázisban.
     * @param user felhasználó
     * @return a felhasználóhoz tartozó összes jegy
     */
    @Override
    public List<Ticket> getUserTicket(User user) {
        Query query = em.createQuery("SELECT ticket FROM Ticket ticket WHERE ticket.user.id = :user_id");
        query.setParameter("user_id", user.getId());
        List<Ticket> tickets = query.getResultList();
        if( tickets == null ) {
            return new ArrayList<>();
        }
        return tickets;
    }

    /**
     * Jegyfoglalás elmentése az adatbázisba.
     * @param ticket jegy
     * @return a perzisztens jegy objektum
     */
    @Override
    public Ticket bookTicket(Ticket ticket) {
        Query query = em.createQuery("SELECT COUNT(ticket.id) FROM Ticket ticket WHERE ticket.sector.id = :sector_id");
        query.setParameter("sector_id", ticket.getSector().getId());
        int count = ((Number) query.getSingleResult()).intValue();

        /*
        if( count > (ticket.getSector().getNumOfCols() * ticket.getSector().getNumOfCols()) ) {
            return null;
        }
        */


        if( ticket.getCol() == null || ticket.getRow() == null ) {
            // állóhelyes
            em.persist(ticket);
        } else {
            // ülőhelyes
            em.persist(ticket);
        }
        User user = em.find(User.class, ticket.getUser().getId());
        em.refresh(user);
        em.flush();

        return ticket;
    }

    /**
     * Foglalás törlése az adatbázisból.
     * @param ticket a törölni kívánt jegy
     */
    /*@Override
    public boolean deleteTicket(Ticket ticket) {
        try{
            ticket = em.getReference(Ticket.class, ticket.getId());
            em.remove(ticket);
            em.flush();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
    @Override
    public boolean deleteTicket(int ticketID) {
        try{
            Query query = em.createQuery("SELECT ticket FROM Ticket ticket");
            List<Ticket> tickets = query.getResultList();
            Ticket ticket = new Ticket();
            for(Ticket tic : tickets){
                if(tic.getId() == ticketID){
                    ticket = tic;
                }
            }
            if(ticket != null){
                em.remove(ticket);
                em.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Ticket> getAllTicket() {
        Query query = em.createQuery("SELECT ticket FROM Ticket ticket");
        List<Ticket> tickets = query.getResultList();
        if( tickets == null ) {
            return new ArrayList<>();
        }
        return tickets;
    }

    /**
     * Adatbázisból kikeresi a jegyhez tartozó szektort.
     * @param ticket jegy
     * @return a jegyhez tartozó szektor
     */
    @Override
    public Sector getSectorByTicket(Ticket ticket) {
        List<Ticket> tickets = getAllTicket();
        for(Ticket t : tickets) {
            if( t.getId().equals(ticket.getId()) ) {
                return t.getSector();
            }
        }
        return null;
    }
}
