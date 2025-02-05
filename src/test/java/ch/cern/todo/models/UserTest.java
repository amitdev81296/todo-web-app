package ch.cern.todo.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void testConstructor() {
        User user = new User("user", "user", "username", "password", "user@gmail.com", "USER");
        assert user.getFirstName().equals("user");
        assert user.getLastName().equals("user");
        assert user.getUsername().equals("username");
        assert user.getPassword().equals("password");
        assert user.getEmail().equals("user@gmail.com");
        assert user.getUserRole().equals("USER");
    }

}
