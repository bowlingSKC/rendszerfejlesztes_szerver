package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Sector;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SectorConnector {

    Sector getSectorByTicketId(Integer id);
    List<Sector> getSectorByEventId(Integer id);

    Sector getSectorById(Integer id);
}
