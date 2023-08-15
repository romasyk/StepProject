package structure.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.dao.CollectionsUserDao;
import structure.dao.UserDao;
import structure.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDaoTest {
    private UserDao userDao;
    private List<User> mockUsers;

    @BeforeEach
    void setUp() {
        userDao = new CollectionsUserDao();
        mockUsers = new ArrayList<>();
        mockUsers.add(new User("Alice", "Johnson"));
        mockUsers.add(new User("Bob", "Smith"));
        mockUsers.add(new User("Charlie", "Brown"));
        userDao.setUsers(mockUsers);
    }



    @Test
    void testGetUserByName() {
        User alice = userDao.getUserByName("Alice", "Johnson");
        assertNotNull(alice);
        assertEquals("ALICE", alice.getPassengerName());
        assertEquals("JOHNSON", alice.getPassengerSurname());
    }

    @Test
    void testGetUserByNameNotFound() {
        User notFound = userDao.getUserByName("Eve", "Doe");
        assertNotNull(notFound);
        assertEquals("UNKNOWN", notFound.getPassengerName());
        assertEquals("UNKNOWN", notFound.getPassengerSurname());
    }

    @Test
    void testCreateUser() {
        User newUser = userDao.createUser("David", "Lee");
        assertNotNull(newUser);
        assertEquals("DAVID", newUser.getPassengerName());
        assertEquals("LEE", newUser.getPassengerSurname());
    }

    @Test
    void testSaveUser() {
        User modifiedUser = new User("Alice", "Johnson");

        userDao.saveUser(modifiedUser);
        User savedUser = userDao.getUserByName("Alice", "Johnson");

        assertNotNull(savedUser);
        assertEquals("ALICE", savedUser.getPassengerName());
        assertEquals("JOHNSON", savedUser.getPassengerSurname());
    }

    @Test
    void testSaveNewUser() {
        User newUser = new User("Eve", "Doe");

        userDao.saveUser(newUser);
        User savedUser = userDao.getUserByName("Eve", "Doe");

        assertNotNull(savedUser);
        assertEquals("EVE", savedUser.getPassengerName());
        assertEquals("DOE", savedUser.getPassengerSurname());
    }
}
