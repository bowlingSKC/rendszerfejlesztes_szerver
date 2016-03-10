package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link pe.rendszerfejlesztes.services.LocationServiceLocal} egy implementált osztálya relációs adatbázisok perzisztens rétegének megvalósításához.
 * Az osztály egyben egy állapot nélküli EJB is.
 * @see pe.rendszerfejlesztes.modell.Location
 */
@Stateless
public class LocationService implements LocationServiceLocal {

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
     * Az adatbázisban található összes esemény listájával tér vissza.
     * Üres talált esetén üres listával tér vissza.
     * @return az adatbázisban található események listája
     */
    @Override
    public List<Location> getAllLocation() {
        Query query = em.createQuery("SELECT location FROM Location location");
        List<Location> locations = query.getResultList();
        if( locations == null ) {
            return new ArrayList<>();
        }
        return locations;
    }
}
