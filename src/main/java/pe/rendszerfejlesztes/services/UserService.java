package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.database.TicetConnectorImpl;
import pe.rendszerfejlesztes.database.TicketConnector;
import pe.rendszerfejlesztes.database.UserConnector;
import pe.rendszerfejlesztes.database.UserConnectorImpl;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;


public class UserService {


    private UserConnector userConnector = new UserConnectorImpl();
    private TicketConnector ticketConnector = new TicetConnectorImpl();

    public User createUser(User newUser) {
        List<User> users = userConnector.searchUser(newUser);
        if( users.size() != 0 ) {
            return null;
        }

        userConnector.createUser(newUser);
        return newUser;
    }

    public User login(String email, String pswd) {
        User login = userConnector.login(email, pswd);
        return login;
    }

    public List<User> listUsers() {
        List<User> users = userConnector.listUsers();
        return users;
    }

    public List<User> searchUser(User user) {
        List<User> users = userConnector.searchUser(user);
        return users;
    }

    public Ticket setTicketPaid(Ticket ticket){
        ticketConnector.setTicketPaid(ticket);
        return ticket;
    }
}
