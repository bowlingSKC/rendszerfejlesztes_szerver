package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.UserServiceLocal;

import javax.ejb.EJB;
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
public class UserService {

    /**
     * A felhasználók kezelését szolgáló EJB.
     * @see pe.rendszerfejlesztes.services.UserServiceLocal
     */
    @EJB
    private UserServiceLocal userService;

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
        System.out.println("Sikeres regisztracio tortent! " + created);
        return Response.ok(created).build();
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
        System.out.println( "Keres erkezett: bejelentkezes" );
        User user = userService.login(email, pswd);
        if( user == null ) {
            System.out.println( "A bejelentkezes nem sikerult" );
        } else {
            System.out.println("A bejelentkezes sikerult: " + user);
        }
        return Response.ok(user).build();
    }


    @GET
    @Produces("application/json")
    //@Path("{email}/{pswd}")
    public Response getUsers() {
        System.out.println( "Keres erkezett: felhasznalok lekerese" );
        List<User> users = userService.listUsers();
        GenericEntity<List<User>> usersWrapper = new GenericEntity<List<User>>(users) {};
        return Response.ok(usersWrapper).build();
    }

}
