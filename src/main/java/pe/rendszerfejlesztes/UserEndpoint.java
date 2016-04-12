package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * A felhasználók kezelésére szolgáló végpont.
 * <p>
 *     Elérése: /api/user
 * </p>
 */
@Path("user")
public class UserEndpoint {

    private UserService userService = new UserService();

    /**
     * Új regisztráció elmentése adatbázisba.
     * Az érvényességeket klines oldalon kell ellenőrizi.
     * <p>
     *     Elérése PUT hívással: /api/user
     * </p>
     * @param newUser az új felhasználó
     * @return a perzisztens felhasználó objektum
     */
    @PUT
    @Produces("application/json")
    public Response registerUser(User newUser) {
        User created = userService.createUser(newUser);
        return Response.ok(created).build();
    }

    /**
     * Adatbázisban való keresése a megadott feltételek alapján.
     * @param user keresési feltételek
     * @return a keresési feltételeknek megfelelő felhasználók listája
     */
    @POST
    @Produces("application/json")
    @Path("search")
    public Response searchUser(User user) {
        List<User> users = userService.searchUser(user);
        GenericEntity<List<User>> usersWrapper = new GenericEntity<List<User>>(users) {};
        return Response.ok(usersWrapper).build();
    }

    /**
     * A rendszerbe való bejelentkezés ellenőrzése. Helytelen bejelentkezés esetén null értékkel tér vissza.
     * <p>
     *     Elérés GET hívással: /api/user/{email}/{jelszo}.
     *     Ahol az {email} cím helyére a felhasználó által megadott E-mail címet, a {jelszo} helyére a felhasználó által
     *     megadott jelszót kell írni.
     * </p>
     * @param email a felhasználó által megadott E-mail cím
     * @param pswd a felhasználó által megadott jelszó titkosítva
     * @return sikeres bejelentkezés esetén az E-mail címhez tartozó objektum, ellenkező esetben null
     */
    @GET
    @Produces("application/json")
    @Path("{email}/{pswd}")
    public Response login(@PathParam("email") String email, @PathParam("pswd") String pswd) {
        User user = userService.login(email, pswd);
        return Response.ok(user).build();
    }

    /**
     * Adatbázisból visszaadja az összes regisztrált felhasználót.
     * @return az összes felhasználó listája
     */
    @GET
    @Produces("application/json")
    public Response login() {
        List<User> users = userService.listUsers();
        GenericEntity<List<User>> usersWrapper = new GenericEntity<List<User>>(users) {};
        return Response.ok(usersWrapper).build();
    }

    /**
     * A megadott jegyet állítja át fizetettre.
     * @return az átállított jegy
     */
    @PUT
    @Path("paid")
    @Produces("application/json")
    public Response setPaidTicket(Ticket ticket) {
        userService.setTicketPaid(ticket);
        return Response.ok(ticket).build();
    }

}
