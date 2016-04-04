package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if( ticket.getCol() == null || ticket.getRow() == null ) {
            // állóhelyes
            Query query = em.createQuery("SELECT COUNT(ticket.id) FROM Ticket ticket WHERE ticket.sector.id = :sector_id");
            query.setParameter("sector_id", ticket.getSector().getId());
            long reserved = (long) query.getSingleResult();
            System.out.println("Ennyi foglalt van mar: " + reserved);
            em.persist(ticket);
        } else {
            // ülőhelyes
            Query query = em.createQuery("SELECT ticket FROM Ticket ticket WHERE ticket.sector.id = :sector_id");
            query.setParameter("sector_id", ticket.getSector().getId());
            List<Ticket> tickets = query.getResultList();
            for(Ticket t : tickets) {
                if(Objects.equals(ticket.getCol(), t.getCol()) && Objects.equals(ticket.getRow(), t.getRow())) {
                    System.out.println("Foglalt helyre erkezett foglalasi keres");
                    return null;
                }
            }
            em.persist(ticket);
        }
        User user = em.find(User.class, ticket.getUser().getId());
        Sector sector = em.find(Sector.class, ticket.getSector().getId());
        em.refresh(user);
        em.refresh(sector);
        em.flush();

        return ticket;
    }

    /**
     * Foglalás törlése az adatbázisból.
     */
    @Override
    public boolean deleteTicket(Ticket ticket) {
        Query query = em.createQuery("SELECT sector.id FROM Sector sector JOIN sector.tickets ticket WHERE ticket.id = :id");
        query.setParameter("id", ticket.getId());
        Integer id = (Integer) query.getSingleResult();

        Ticket delete = em.find(Ticket.class, ticket.getId());
        em.remove(delete);

        Sector sector = em.find(Sector.class, id);
        em.refresh(sector);
        em.flush();

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
