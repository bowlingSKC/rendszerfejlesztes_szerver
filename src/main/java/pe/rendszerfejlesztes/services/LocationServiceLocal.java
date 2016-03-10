package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Local;
import java.util.List;

/**
 * A helyszínek adatbázisműveletét támogató interfész.
 * Az összes implementációnak tartalmaznia kell ezeket a műveleteket a teljes működéshez.
 * @see pe.rendszerfejlesztes.modell.Location
 */
@Local
public interface LocationServiceLocal {

    /**
     * Az adatbázisban található összes esemény listájával tér vissza.
     * Üres talált esetén üres listával tér vissza.
     * @return az adatbázisban található események listája
     */
    List<Location> getAllLocation();

}
