package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.EventConnector;
import pe.rendszerfejlesztes.modell.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventConnectorImpl implements EventConnector {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public List<Event> getAllEvents() {
        Query query = em.createQuery("SELECT event FROM Event event").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        List<Event> events = query.getResultList();
        if( events == null ) {
            return new ArrayList<>();
        }
        return events;
    }

    @Override
    public List<Event> searchEvent(Event event) {
        StringBuilder builder = new StringBuilder("SELECT event FROM Event event ");
        boolean insertedWhere = false;
        if( event.getName() != null && !event.getName().equals("") ) {
            if( !insertedWhere ) {
                builder.append("WHERE ");
                insertedWhere = true;
            }
            builder.append("event.name LIKE '%" + event.getName() +  "%' AND ");
        }
        if( event.getLocation() != null ) {
            if( !insertedWhere ) {
                builder.append("WHERE ");
                insertedWhere = true;
            }
            builder.append("event.location.id = " + event.getLocation().getId() + " AND ");
        }
        if( event.getPerformer() != null && !event.getPerformer().getName().equals("") ) {
            if( !insertedWhere ) {
                builder.append("WHERE ");
                insertedWhere = true;
            }
            builder.append("AND event.performer.name = '%" + event.getPerformer().getName() + "%' AND ");
        }

        System.out.println(builder.toString());

        Query query;
        if( builder.toString().lastIndexOf("AND ") != -1 ) {
            query = em.createQuery(builder.toString().substring(0, builder.toString().lastIndexOf("AND "))).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        } else {
            query = em.createQuery(builder.toString()).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        }
        List<Event> events = query.getResultList();
        if( events == null ) {
            return new ArrayList<>();
        }
        return events;
    }

    @Override
    public Event getEventBySectorId(Integer id) {
        Query query = em.createQuery("SELECT sector FROM Sector sector WHERE sector.event.id = :id").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        query.setParameter("id", id);
        return (Event) query.getSingleResult();
    }
}
