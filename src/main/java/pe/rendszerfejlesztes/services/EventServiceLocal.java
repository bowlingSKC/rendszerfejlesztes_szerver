package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Event;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EventServiceLocal {

    List<Event> getAllEvents();
    List<Event> searchEvent(Event event);

}
