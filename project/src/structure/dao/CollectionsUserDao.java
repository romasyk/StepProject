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

    public void deleteReservationById(String id){

    }
    public String showAllReservations(){
        return null;
    }


}
