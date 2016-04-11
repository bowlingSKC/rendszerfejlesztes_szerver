package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.TicketConnector;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TicetConnectorImpl implements TicketConnector {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public List<Ticket> getTicketsByUserId(Integer userId) {
        Query query = em.createQuery("SELECT ticket FROM Ticket ticket WHERE ticket.user.id = :id").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        query.setParameter("id", userId);
        List<Ticket> tickets = query.getResultList();

        if( tickets == null ) {
            return new ArrayList<>();
        }
        return tickets;
    }

    @Override
    public Ticket bookTicket(Ticket ticket) {
        em.persist(ticket);

        User user = em.find(User.class, ticket.getUser().getId());
        Sector sector = em.find(Sector.class, ticket.getSector().getId());
        em.refresh(user);
        em.refresh(sector);

        em.flush();
        return ticket;
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        em.remove(ticket);

        User user = em.find(User.class, ticket.getUser().getId());
        Sector sector = em.find(Sector.class, ticket.getSector().getId());
        em.refresh(user);
        em.refresh(sector);

        em.flush();
    }

    @Override
    public void setTicketPaid(Ticket ticket) {
        Ticket db = em.find(Ticket.class, ticket.getId());
        db.setPaid(true);
        em.refresh(db);
        em.flush();
    }
}