package structure.service;

import structure.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByName(String name, String surname);
    void saveUser(String name, String surname);
    User createUser(String name, String surname);
}
