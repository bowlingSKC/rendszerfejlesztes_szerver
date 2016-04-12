package pe.rendszerfejlesztes.database;


import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SubscriptionConnectorImpl implements SubscriptionConnector {

    EntityManager em = EmFactory.getEntityManager();

    private EventConnector eventConnector = new EventConnectorImpl();

    @Override
    public Subscription subscribe(User user, Integer id){
        List<Event> events = eventConnector.getAllEvents();
        Event event = new Event();
        for(Event e : events){
            if(e.getId() == id){
                event = e;
                Subscription subscription = new Subscription(user,event);
                em.getTransaction().begin();
                em.persist(subscription);
                em.getTransaction().commit();
                return subscription;
            }
        }
        return null;
    }

    @Override
    public List<Subscription> getUserSubscription(User user){
        Query query = em.createQuery("SELECT subscription FROM Subscription subscription");
        List<Subscription> tempSubscriptions = query.getResultList();
        if( tempSubscriptions == null ) {
            return new ArrayList<>();
        }
        List<Subscription> subscriptions = new ArrayList<>();
        for(Subscription sub : tempSubscriptions){
            if(sub.getUser().getId() == user.getId()){
                subscriptions.add(sub);
            }
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> getAllSubscription(){
        Query query = em.createQuery("SELECT subscription FROM Subscription subscription");
        List<Subscription> subscriptions = query.getResultList();
        if( subscriptions == null ) {
            return new ArrayList<>();
        }
        return subscriptions;
    }

    @Override
    public Event getSubscriptionByEventId(Integer id) {
        /*Query query = em.createQuery("SELECT sub FROM Subscription sub WHERE sub.event.id = :id");
        query.setParameter("id", id);
        Event result = (Event) query.getSingleResult();*/

        List<Event> events =  em.createQuery("SELECT event FROM Event event").getResultList();
        List<Subscription> subscriptions = getAllSubscription();
        for(Subscription s : subscriptions){
            if(s.getId() == id){
                for(Event e : events){
                    if(e.getId() == s.getEvent().getId()){
                           return e;
                    }
                }
            }
        }
        return null;
        //return result;
    }
}
