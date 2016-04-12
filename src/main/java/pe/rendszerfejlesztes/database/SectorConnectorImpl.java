package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    @Override
    public Sector getSectorById(Integer id) {
        Sector sector = em.find(Sector.class, id);
        return sector;
    }


}
