package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        if (user == null) {
            logger.log(Level.SEVERE, "saveUser: user is null");
        }
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            logger.log(Level.SEVERE, "removeUserById: user is null");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("from User", User.class).getResultList();
        if (!allUsers.isEmpty()) {
            return allUsers;
        }
        logger.log(Level.SEVERE, "allUsers is empty");
        return new ArrayList<>();
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            entityManager.merge(user);
        } else {
            logger.log(Level.SEVERE, "user is not updated");
        }
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return user;
        }
        logger.log(Level.SEVERE, "user is not found");
        return new User();
    }
}
