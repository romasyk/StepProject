package structure.dao;

import structure.model.User;

import java.util.List;

public interface UserDao {
     List<User> getUsers();
     User getUserByName(String name, String surname);
     User createUser(String name, String surname);
     void saveUser(User user);
     void setUsers(List<User> users);
     void downloadUsers();
     void loadData();
}
