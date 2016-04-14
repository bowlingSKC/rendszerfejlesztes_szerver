package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.EventConnector;
import pe.rendszerfejlesztes.database.LocationConnector;
import pe.rendszerfejlesztes.database.PerformerConnector;
import pe.rendszerfejlesztes.database.impl.EventConnectorImpl;
import pe.rendszerfejlesztes.database.SectorConnector;
import pe.rendszerfejlesztes.database.impl.LocationConnectorImpl;
import pe.rendszerfejlesztes.database.impl.PerformerConnectorImpl;
import pe.rendszerfejlesztes.database.impl.SectorConnectorImpl;
import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Location;
import pe.rendszerfejlesztes.modell.Performer;
import pe.rendszerfejlesztes.modell.Sector;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    private EventConnector eventConnector = new EventConnectorImpl();
    private SectorConnector sectorConnector = new SectorConnectorImpl();
    private PerformerConnector performerConnector = new PerformerConnectorImpl();
    private LocationConnector locationConnector = new LocationConnectorImpl();

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

    public Event createNewEvent(Event event) {
        Performer performer = performerConnector.getPerformerById(event.getPerformer().getId());
        event.setPerformer(performer);

        Location location = locationConnector.getLocationById(event.getLocation().getId());
        event.setLocation(location);

        if(event.getLocation().getEvents() == null) {
            event.getLocation().setEvents(new ArrayList<Event>());
        }
        if(event.getPerformer().getEvents() == null) {
            event.getPerformer().setEvents(new ArrayList<Event>());
        }
        event.getPerformer().getEvents().add(event);
        for(Sector sector : event.getSectorList()) {
            sector.setEvent(event);
        }
        Event created = eventConnector.createNewEvent(event);
        return created;
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
