package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserDao userDaoHibernate;

    @Autowired
    public UserServiceImpl(UserDao userDaoHibernate) {
        this.userDaoHibernate = userDaoHibernate;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        Map<String, String> errors = new HashMap<>();
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            errors.put("nameError", "name cannot be empty");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            errors.put("lastNameError", "lastName cannot be empty");
        } else if (user.getAge() > 100 || user.getAge() < 0) {
            errors.put("ageError", "age must be between 0 and 100");
        }
        if (!errors.isEmpty()) {
            logger.log(Level.SEVERE, errors.toString());
        } else {
            userDaoHibernate.saveUser(user);
        }
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> allUsers = userDaoHibernate.getAllUsers();
        return allUsers;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDaoHibernate.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        User user = userDaoHibernate.getUserById(id);
        return user;
    }
}