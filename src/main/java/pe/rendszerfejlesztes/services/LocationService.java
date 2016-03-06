package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LocationService implements LocationServiceLocal {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

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
