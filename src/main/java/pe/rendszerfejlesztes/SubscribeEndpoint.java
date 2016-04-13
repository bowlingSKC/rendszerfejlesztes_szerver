package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.Event;
import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.SubscribeService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subscribe")
public class SubscribeEndpoint {

    private SubscribeService subscribeService = new SubscribeService();

    @POST
    @Produces("application/json")
    @Path("subscribe/{id}")
    public Response subscribe(User user, @PathParam("id") Integer id) {
        System.out.println("HERE");
        System.out.println( "Erkezett: " + user.toString() + " - " + id.toString() );
        Subscription sub = subscribeService.subscribe(user, id);
        return Response.ok(sub).build();
    }

    @POST
    @Path("getSub")
    @Produces("application/json")
    public Response getUserSubscriptions(User user) {
        System.out.println( "Keres erkezett: osszes feliratkozas lekerdezese" );
        List<Subscription> subscriptions = subscribeService.getUserSubscription(user);
        GenericEntity<List<Subscription>> subscriptionWrapper = new GenericEntity<List<Subscription>>(subscriptions) {};
        return Response.ok(subscriptionWrapper).build();
    }


    @GET
    @Produces("application/json")
    @Path("bysubscription/{id}")
    public Response getSubscriptionByEventId(@PathParam("id") Integer id) {
        Event event = subscribeService.getSubscriptionByEventId(id);
        return Response.ok(event).build();
    }

    @POST
    @Path("unsubscribe")
    @Produces("application/json")
    public Response unSubscribe(Subscription subscription) {
        System.out.println( "Keres erkezett leiratkozasra:" + subscription.toString() );
         if(subscribeService.unSubscribe(subscription)){
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }
    }

    @POST
    @Produces("application/json")
    @Path("issubscribed/{id}")
    public Response subscribe(Event event, @PathParam("id") Integer id) {
        System.out.println("HERE2");
        System.out.println( "IsSubscrube keres: " + event.toString() + " - " + id.toString() );
        if(subscribeService.isSubscribed(event, id)){
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }
    }

}
