package ch.cern.todo.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TaskTest {

    @Test
    public void testTaskConstructor() {
        Category category = new Category("Sample Category", "Sample Description");
        User user = new User("user", "user", "username", "password", "email@gmail.com", "USER");
        Task task = new Task("taskName", "taskDescription", LocalDate.now(), category, user);
        assert task.getTaskName().equals("taskName");
        assert task.getTaskDescription().equals("taskDescription");
        assert task.getCategory().getCategoryName().equals(category.getCategoryName());
        assert task.getUser().getUsername().equals("username");
    }

}
