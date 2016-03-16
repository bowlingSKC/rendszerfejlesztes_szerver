package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Sector;

import javax.ejb.Local;
import java.util.List;

/**
 * Az események adatbázisműveletét támogató interfész.
 * Az összes implementációnak tartalmaznia kell ezeket a műveleteket a teljes működéshez.
 * @see pe.rendszerfejlesztes.modell.Event
 */
@Local
public interface EventServiceLocal {

    /**
     * Adatbázisból lekérdezi az összes eseményt.
     * Ha nincs egy esemény se, akkor üres listával tér vissza.
     * @return az események listája
     */
    List<Event> getAllEvents();

    /**
     * Keresés az események között a megadott szempontok szerint.
     * Ha egy eseményt se talál, akkor üres listával tér vissza.
     * @param event a keresési paraméter
     * @return a keresési feltételeknek megfelelő események listája
     */
    List<Event> searchEvent(Event event);

    /**
     * Adatbázisból lekérdezi egy eseményhez tartozó szektorok listáját.
     * @param id az esemény elsődleges kulcsa
     * @return az eseményhez tartozó szektorok listája
     */
    List<Sector> getSectorsByEventId(Integer id);

    /**
     * Adatbázisból kikeresi a paraméterben megadott szektorhoz tartozó eseményt.
     * @param sector szektor
     * @return a szektorhoz tartozó esemény
     */
    Event getEventBySector(Sector sector);

}
