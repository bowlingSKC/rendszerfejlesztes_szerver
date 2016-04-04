package pe.rendszerfejlesztes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * A szerver tesztelésére szolgáló végpont.
 * A végpontot az alábbi útvonalon lehet elérni: /api/test
 */
@Path("test")
public class TestEndpoint {

    /**
     * A szerver működését ellenőrző végpont.
     * Működés esetén egy sztringgel tér vissza, ellenkező esetben hibaüzenettel.
     * <p>
     *     A végpontot elérni a következő GET utasítással lehetséges: /api/test
     * </p>
     * @return konstans string ("OK")
     */
    @GET
    @Produces("text/plain")
    public Response ping() {
        return Response.ok("OK").build();
    }

}
