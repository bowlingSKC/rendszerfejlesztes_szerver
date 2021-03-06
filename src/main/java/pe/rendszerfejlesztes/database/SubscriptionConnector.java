package pe.rendszerfejlesztes.database;


import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SubscriptionConnector {
    Subscription subscribe(User user, Integer id);
    List<Subscription> getUserSubscription(User user);
    List<Subscription> getAllSubscription();
    boolean unSubscribe(Subscription subscription);
    Event getSubscriptionByEventId(Integer id);
    boolean isSubscribed(Event event, Integer id);
}
