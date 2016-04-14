package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Performer;

import java.util.List;

public interface PerformerConnector {

    List<Performer> getAllPerformer();
    Performer createPerformer(Performer performer);

    Performer getPerformerById(Integer id);
}
