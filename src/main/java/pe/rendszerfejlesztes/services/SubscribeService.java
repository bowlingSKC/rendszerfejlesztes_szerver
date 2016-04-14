package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.SubscriptionConnector;
import pe.rendszerfejlesztes.database.impl.SubscriptionConnectorImpl;
import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;

import java.util.List;

public class SubscribeService  {

    private SubscriptionConnector subscriptionConnector = new SubscriptionConnectorImpl();

    public Subscription subscribe(User user, Integer id){
        return subscriptionConnector.subscribe(user,id);

        /*List<Event> events = eventService.getAllEvents();
        Event event = new Event();
        for(Event e : events){
            if(e.getId() == id){
                event = e;
                Subscription subscription = new Subscription(user,event);
                em.persist(subscription);
                return subscription;
            }
        }
        return null;*/
    }

    public List<Subscription> getUserSubscription(User user){
        return subscriptionConnector.getUserSubscription(user);
        /*Query query = em.createQuery("SELECT subscription FROM Subscription subscription");
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
        return subscriptions;*/
    }

    public List<Subscription> getAllSubscription(){
        return subscriptionConnector.getAllSubscription();
        /*Query query = em.createQuery("SELECT subscription FROM Subscription subscription");
        List<Subscription> subscriptions = query.getResultList();
        if( subscriptions == null ) {
            return new ArrayList<>();
        }
        return subscriptions;*/
    }

    public Event getSubscriptionByEventId(Integer id) {
        return subscriptionConnector.getSubscriptionByEventId(id);
    }

    public boolean unSubscribe(Subscription subscription){
        return subscriptionConnector.unSubscribe(subscription);
    }

    public boolean isSubscribed(Event event, Integer id){
        return  subscriptionConnector.isSubscribed(event, id);
    }
}
