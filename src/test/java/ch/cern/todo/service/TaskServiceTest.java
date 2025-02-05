package ch.cern.todo.service;

import ch.cern.todo.models.User;
import ch.cern.todo.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testGetTasksForUser() {
        User user = new User();
        taskService.getTasksForUser(user);
        verify(taskRepository, times(1)).findByUser(user);
    }

    @Test
    public void testAllTasksOrderByUser() {
        taskService.getAllTasksOrderByUser();
        verify(taskRepository, times(1)).findAllByOrderByUserAsc();
    }

}
