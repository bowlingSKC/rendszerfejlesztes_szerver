package pe.rendszerfejlesztes.database.impl;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import pe.rendszerfejlesztes.database.EmFactory;
import pe.rendszerfejlesztes.database.UserConnector;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserConnectorImpl implements UserConnector {

    EntityManager em = EmFactory.getEntityManager();

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        List<User> users = listUsers();
        for(User user : users) {
            if( user.getEmail().equals( email.trim() ) ) {
                if( user.getPassword().equals( password.trim() ) ) {
                    return user;
                }
                return null;
            }
        }
        return null;
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
