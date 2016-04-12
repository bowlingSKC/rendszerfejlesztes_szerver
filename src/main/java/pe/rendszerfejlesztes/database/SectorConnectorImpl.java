package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.database.SectorConnector;
import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SectorConnectorImpl implements SectorConnector {

    EntityManager em = EmFactory.getEntityManager();

    @Override
    public Sector getSectorByTicketId(Integer id) {
        Ticket ticket = em.find(Ticket.class, id);
        return ticket.getSector();
    }

    @Override
    public List<Sector> getSectorByEventId(Integer id) {
        Event event = em.find(Event.class, id);
        return event.getSectorList();
    }


}
