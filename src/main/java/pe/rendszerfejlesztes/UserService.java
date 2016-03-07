package pe.rendszerfejlesztes;

import pe.rendszerfejlesztes.modell.User;
import pe.rendszerfejlesztes.services.UserServiceLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    @EJB
    private UserServiceLocal userService;

    @PUT
    @Produces("application/json")
    public Response registerUser(User newUser) {
        User created = userService.createUser(newUser);
        System.out.println("Sikeres regisztracio tortent! " + created);
        return Response.ok(created).build();
    }

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

}
