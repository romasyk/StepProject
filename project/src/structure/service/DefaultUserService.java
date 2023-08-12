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
}
