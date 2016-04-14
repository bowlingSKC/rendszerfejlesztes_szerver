package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocationConnector {

    List<Location> getAllLocation();

    Location getLocationById(Integer id);
}
