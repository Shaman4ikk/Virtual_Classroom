package reprository;

import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {


    @Before
    public void setUp() throws Exception {
        UserRepository.addToListUser(new User("test", true));
    }

    @Test
    public void testGetUsersList() {
        List<User> usersList = UserRepository.getUsersList();

        assertEquals("test", usersList.get(0).getName());
    }

    @Test
    public void testAddToListUser() {
        UserRepository.addToListUser(new User("test1", true));

        List<User> usersList = UserRepository.getUsersList();

        assertEquals("test1", usersList.get(1).getName());
    }

}