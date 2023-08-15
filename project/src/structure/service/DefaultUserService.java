package structure.service;

import structure.dao.UserDao;
import structure.model.User;

import java.util.List;

public class DefaultUserService implements UserService {
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserByName(String name, String surname) {
        return userDao.getUserByName(name,surname);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User createUser(String name, String surname) {
        return userDao.createUser(name, surname);
    }

    @Override
    public void downloadUsers() {
        userDao.downloadUsers();
    }

    @Override
    public void loadData() {
userDao.loadData();
    }


    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
