package pe.rendszerfejlesztes.database.impl;

import pe.rendszerfejlesztes.database.EmFactory;
import pe.rendszerfejlesztes.database.PerformerConnector;
import pe.rendszerfejlesztes.modell.Performer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PerformerConnectorImpl implements PerformerConnector {

    private EntityManager em = EmFactory.getEntityManager();

    @Override
    public List<Performer> getAllPerformer() {
        Query query = em.createQuery("SELECT performer FROM Performer performer");
        List<Performer> performers = query.getResultList();
        if( performers == null ) {
            return new ArrayList<>();
        }
        return performers;
    }

    @Override
    public Performer createPerformer(Performer performer) {
        em.getTransaction().begin();
        em.persist(performer);
        em.flush();
        em.getTransaction().commit();
        return performer;
    }

    @Override
    public Performer getPerformerById(Integer id) {
        Performer performer = em.find(Performer.class, id);
        return performer;
    }
}
