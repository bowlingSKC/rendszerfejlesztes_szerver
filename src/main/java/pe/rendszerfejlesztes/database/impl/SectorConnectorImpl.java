package pe.rendszerfejlesztes.database.impl;

import pe.rendszerfejlesztes.database.SectorConnector;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SectorConnectorImpl implements SectorConnector {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public Sector getSectorByTicketId(Integer id) {
        Ticket ticket = em.find(Ticket.class, id);
        return ticket.getSector();
    }
}
