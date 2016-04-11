package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.LocationConnector;
import pe.rendszerfejlesztes.modell.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LocationConnectorImpl implements LocationConnector {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;


    @Override
    public List<Location> getAllLocation() {
        Query query = em.createQuery("").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        List<Location> locations = query.getResultList();
        if( locations == null ) {
            return new ArrayList<>();
        }
        return locations;
    }
}
