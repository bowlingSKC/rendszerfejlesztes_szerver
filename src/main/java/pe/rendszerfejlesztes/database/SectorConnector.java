package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Sector;

public interface SectorConnector {

    Sector getSectorByTicketId(Integer id);

}
