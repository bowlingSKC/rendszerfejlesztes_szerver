package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.EventConnector;
import pe.rendszerfejlesztes.database.EventConnectorImpl;
import pe.rendszerfejlesztes.database.SectorConnector;
import pe.rendszerfejlesztes.database.SectorConnectorImpl;
import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private EventConnector eventConnector = new EventConnectorImpl();
    private SectorConnector sectorConnector = new SectorConnectorImpl();

    public List<Event> getAllEvents() {
        List<Event> events = eventConnector.getAllEvents();
        return events;
    }

    public List<Event> searchEvent(Event event) {
        List<Event> events = eventConnector.searchEvent(event);
        return events;
    }

    public List<Sector> getSectorsByEventId(Integer id) {
        List<Sector> sectors = sectorConnector.getSectorByEventId(id);
        return sectors;
    }

    public Event getEventBySector(Sector sector) {
        List<Event> allEvents = getAllEvents();
        for(Event event : allEvents) {
            for(Sector sec : event.getSectorList()) {
                if( sec.getId().equals(sector.getId()) ) {
                    return event;
                }
            }
        }
        return null;
    }

    /*
    TODO
    public Event getSubscriptionByEventId(Integer id){
        List<Event> events = getAllEvents();
        List<Subscription> subscriptions = subscribeService.getAllSubscription();
        for(Subscription s : subscriptions){
            if(s.getId() == id){
                for(Event e : events){
                    if(e.getId() == s.getEvent().getId()){
                        return e;
                    }
                }
            }
        }
        return null;
    }
    */
}
