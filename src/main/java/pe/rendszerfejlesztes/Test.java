package pe.rendszerfejlesztes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("test")
public class Test {

    @GET
    @Produces("text/plain")
    public Response ping() {
        return Response.ok("Got it!").build();
    }

}
