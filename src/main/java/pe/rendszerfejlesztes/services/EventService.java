package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventService implements EventServiceLocal {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public List<Event> getAllEvents() {
        Query query = em.createQuery("SELECT event FROM Event event");
        List<Event> events = query.getResultList();
        if( events == null ) {
            return new ArrayList<>();
        }
        return events;
    }
}
