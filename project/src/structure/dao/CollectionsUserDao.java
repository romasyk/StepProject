package structure.dao;

import structure.model.Flight;
import structure.model.User;

import java.io.*;
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
        for (User user : this.users) {
            if (user.getPassengerName().equals(name) && user.getPassengerSurname().equals(surname)) {
                return user;
            }
        }
        return new User("unknown","unknown");
    }

    @Override
    public User createUser(String name, String surname){
        return new User(name,surname);
    }

    @Override
    public void saveUser(User user) {
        if (users.contains(user)) {
            int index = users.indexOf(user);
            users.set(index, user);
        } else {
            users.add(user);
        }
    }

    @Override
    public void setUsers(List<User> users) {
        this.users  = users;
    }

    @Override
    public void loadData(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Users.txt"));
            oos.writeObject(users);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void downloadUsers() {
        try {
            this.users = (List<User>) new ObjectInputStream(new FileInputStream("Users.txt")).readObject();
        } catch (FileNotFoundException e){
            System.out.println("Файл не знайдено");
        }catch (EOFException e){
            System.out.println("Даних немає");
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}
