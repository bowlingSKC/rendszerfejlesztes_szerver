package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link pe.rendszerfejlesztes.services.EventServiceLocal} egy implementált osztálya relációs adatbázisok perzisztens rétegének megvalósításához.
 * Az osztály egyben egy állapot nélküli EJB is.
 * @see pe.rendszerfejlesztes.modell.Event
 */
@Stateless
public class EventService implements EventServiceLocal {

    /**
     * Adatbázist megvalósító osztály.
     * Ezen adattagon kereszül kell az adatbázis-műveleteket megvalósítani.
     * <p>
     *     A PersistenceContex annotáció unitName mezőjének az értéke a persistence.xml fájlban van definiálva.
     * </p>
     */
    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    /**
     * Adatbázisból lekérdezi az összes eseményt.
     * Ha nincs egy esemény se, akkor üres listával tér vissza.
     * @return az események listája
     */
    @Override
    public List<Event> getAllEvents() {
        Query query = em.createQuery("SELECT event FROM Event event");
        List<Event> events = query.getResultList();
        if( events == null ) {
            return new ArrayList<>();
        }
        return events;
    }

    /**
     * Keresés az események között a megadott szempontok szerint.
     * Ha egy eseményt se talál, akkor üres listával tér vissza.
     * @param event a keresési paraméter
     * @return a keresési feltételeknek megfelelő események listája
     */
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
            query = em.createQuery(builder.toString().substring(0, builder.toString().lastIndexOf("AND ")));
        } else {
            query = em.createQuery(builder.toString());
        }
        List<Event> events = query.getResultList();
        if( events == null ) {
            return new ArrayList<>();
        }
        return events;
    }
}
