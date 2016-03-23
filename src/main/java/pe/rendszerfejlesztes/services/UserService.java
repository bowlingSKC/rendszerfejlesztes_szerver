package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Az összes felhasználó lekérdezése az adatbázisból.
     * @return a regisztrált felhasználók listája
     */
    @Override
    public List<User> listUsers() {
        Query query = em.createQuery("SELECT user FROM User user");
        List<User> users = query.getResultList();
        if( users == null ) {
            return new ArrayList<>();
        }
        return users;
    }


    /**
     * Adatbázisból visszaadja a felhasználót friss adatokkal.
     * @return a paraméterben megadott felhasználó
     */
    /*@Override
    public User updateUser(User user) {
        Query query = em.createQuery("SELECT user FROM User user");
        List<User> users = query.getResultList();
        for(User usr : users){
            if(usr.getId() == user.getId()){
                return usr;
            }
        }
        return null;
    }*/

    /**
     * Felhasználók keresése az adatbázisban a megadott szempontok szerint.
     * @param user keresési feltételek
     * @return a keresési feltételeknek megfelelő felhasználók listája
     */
    @Override
    public List<User> searchUser(User user) {
        Query query;
        if( !user.getName().equals("") && !user.getEmail().equals("") ) {
            query = em.createQuery("SELECT user FROM User user WHERE user.email LIKE :email AND user.name LIKE :name");
            query.setParameter("email", "%" + user.getEmail() + "%");
            query.setParameter("name", "%" + user.getName() + "%");
        } else if( !user.getName().equals("") ) {
            query = em.createQuery("SELECT user FROM User user WHERE user.name LIKE :name");
            query.setParameter("name", "%" + user.getName() + "%");
        } else if( !user.getEmail().equals("") ) {
            query = em.createQuery("SELECT user FROM User user WHERE user.email LIKE :email");
            query.setParameter("email", "%" + user.getEmail() + "%");
        } else {
            return listUsers();
        }
        List<User> users = query.getResultList();
        if( users == null ) {
            return new ArrayList<>();
        }
        return users;
    }
}
