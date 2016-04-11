package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.UserConnector;
import pe.rendszerfejlesztes.modell.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserConnectorImpl implements UserConnector {

    @PersistenceContext(unitName = "serverUnit")
    EntityManager em;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.email LIKE :email AND user.password LIKE :passwd").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        query.setParameter("email", email);
        query.setParameter("passwd", password);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public List<User> listUsers() {
        Query query = em.createQuery("SELECT user FROM User user").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        List<User> users = query.getResultList();
        if(users == null) {
            return new ArrayList<>();
        }
        return users;
    }

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
