package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserService implements UserServiceLocal {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public User createUser(User newUser) {
        em.persist(newUser);
        return newUser;
    }

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
