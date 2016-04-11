package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Event;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EventConnector {

    List<Event> getAllEvents();
    List<Event> searchEvent(Event event);
    Event getEventBySectorId(Integer id);

}
