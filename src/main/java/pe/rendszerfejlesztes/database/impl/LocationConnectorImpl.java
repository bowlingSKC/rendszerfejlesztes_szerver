package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.EmFactory;
import pe.rendszerfejlesztes.database.LocationConnector;
import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LocationConnectorImpl implements LocationConnector {

    EntityManager em = EmFactory.getEntityManager();


    @Override
    public List<Location> getAllLocation() {
        Query query = em.createQuery("SELECT loc FROM Location loc").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        List<Location> locations = query.getResultList();
        if( locations == null ) {
            return new ArrayList<>();
        }
        return locations;
    }

    @Override
    public Location getLocationById(Integer id) {
        Location location = em.find(Location.class, id);
        return location;
    }
}
