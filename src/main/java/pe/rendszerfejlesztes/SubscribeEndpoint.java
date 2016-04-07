package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.Subscription;
import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.SubscribeServiceLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subscribe")
public class SubscribeEndpoint {

    @EJB
    private SubscribeServiceLocal subscribeService;

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

}
