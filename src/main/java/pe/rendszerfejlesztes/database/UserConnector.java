package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.User;

import java.util.List;

public interface UserConnector {

    User createUser(User user);
    User login(String email, String password);
    List<User> listUsers();
    List<User> searchUser(User user);

}
