package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserConnector {

    User createUser(User user);
    User login(String email, String password);
    List<User> listUsers();
    List<User> searchUser(User user);
    User updateUser(User user);

}
