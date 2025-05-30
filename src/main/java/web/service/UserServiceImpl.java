package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDaoHibernate;

    @Autowired
    public UserServiceImpl(UserDao userDaoHibernate) {
        this.userDaoHibernate = userDaoHibernate;
    }

    @Override
    public void saveUser(User user) {
        userDaoHibernate.saveUser(user);
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userDaoHibernate.getAllUsers();
        return allUsers;
    }

    @Override
    public void updateUser(User user) {
        userDaoHibernate.updateUser(user);
    }

    @Override
    public User getUserById(long id) {
        User user = userDaoHibernate.getUserById(id);
        return user;
    }
}