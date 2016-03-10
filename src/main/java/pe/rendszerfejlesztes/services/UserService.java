package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A {@link pe.rendszerfejlesztes.services.UserServiceLocal} egy implementált osztálya relációs adatbázisok perzisztens rétegének megvalósításához.
 * Az osztály egyben egy állapot nélküli EJB is.
 * @see pe.rendszerfejlesztes.modell.User
 */
@Stateless
public class UserService implements UserServiceLocal {

    /**
     * Adatbázist megvalósító osztály.
     * Ezen adattagon kereszül kell az adatbázis-műveleteket megvalósítani.
     * <p>
     *     A PersistenceContex annotáció unitName mezőjének az értéke a persistence.xml fájlban van definiálva.
     * </p>
     */
    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    /**
     * Felhasználó elmentése az adatbátisba.
     * @param newUser a menteni kívánt felhasználó
     * @return a perzisztens felhasználó
     */
    @Override
    public User createUser(User newUser) {
        em.persist(newUser);
        return newUser;
    }

    /**
     * Bejelentkezés ellenőrzése.
     * @param email a felhasználó által megadott E-mail cím
     * @param pswd a felhasználó által megadott jelszó
     * @return sikeres bejelentkezés esetén az E-mail címhez tartozó felhasználó, ellenkező esetben null
     */
    @Override
    public User login(String email, String pswd) {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.email LIKE :email AND user.password LIKE :pswd");
        query.setParameter("email", email);
        query.setParameter("pswd", pswd);

        User logged;
        try {
            logged = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            logged = null;
        }

        return logged;
    }
}
