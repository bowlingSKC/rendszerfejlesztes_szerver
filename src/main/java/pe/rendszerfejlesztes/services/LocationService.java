package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.LocationConnector;
import pe.rendszerfejlesztes.database.impl.LocationConnectorImpl;
import pe.rendszerfejlesztes.modell.Location;

import java.util.List;

public class LocationService {

    private LocationConnector locationConnector = new LocationConnectorImpl();

    /**
     * Az adatbázisban található összes esemény listájával tér vissza.
     * Üres talált esetén üres listával tér vissza.
     * @return az adatbázisban található események listája
     */
    public List<Location> getAllLocation() {
        List<Location> locations = locationConnector.getAllLocation();
        return locations;
    }
}
