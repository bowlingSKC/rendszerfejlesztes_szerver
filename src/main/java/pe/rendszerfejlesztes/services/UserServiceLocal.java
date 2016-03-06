package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Local;

@Local
public interface UserServiceLocal {

    User createUser(User newUser);
    User login(String email, String pswd);

}
