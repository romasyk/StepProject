package structure.dao;

import structure.model.Flight;
import structure.model.User;

import java.util.List;

public class CollectionsUserDao implements UserDao {
    private List<User> users;

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserByName(String name, String surname) {
        name = name.toUpperCase().trim();
        surname = surname.toUpperCase().trim();
        for (User user : users) {
            if (user.getPassengerName().equals(name) && user.getPassengerSurname().equals(surname)) {
                return user;
            }
        }
        return new User();
    }

    @Override
    public User createUser(String name, String surname){
        return new User(name,surname);
    }

    @Override
    public void saveUser(String name, String surname) {
        User user = getUserByName(name, surname);
       if(!users.contains(user)){
           users.add(createUser(name, surname));
       }
    }

}
