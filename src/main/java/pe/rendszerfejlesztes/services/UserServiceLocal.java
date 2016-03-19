package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Local;
import java.util.List;

/**
 * A felhasználók adatbázisműveletét támogató interfész.
 * Az összes implementációnak tartalmaznia kell ezeket a műveleteket a teljes működéshez.
 * @see pe.rendszerfejlesztes.modell.User
 */
@Local
public interface UserServiceLocal {

    /**
     * Új felhasználó elmentése az adatbázisba.
     * Az adatok érvényességét kliens oldalon kell ellenőrizni.
     * @param newUser a menteni kívánt felhasználó
     * @return a perzisztens felhasználó
     */
    User createUser(User newUser);

    /**
     * Bejelenzkezés ellenőrzése.
     * @param email a felhasználó által megadott E-mail cím
     * @param pswd a felhasználó által megadott jelszó
     * @return sikeres bejelentkezés esetén az E-mail címhez tartozó felhasználó, ellenkező esetben null
     */
    User login(String email, String pswd);

    List<User> listUsers();
    /**
     * Felhasználók keresése az adatbázisban a megadott szempontok szerint.
     * @param user keresési feltételek
     * @return a keresési feltételeknek megfelelő felhasználók listája
     */
    List<User> searchUser(User user);

}
