package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Location;

import javax.ejb.Local;
import java.util.List;

@Local
public interface LocationServiceLocal {

    List<Location> getAllLocation();

}
