package ch.cern.todo.service;

import ch.cern.todo.models.Task;
import ch.cern.todo.models.User;
import ch.cern.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> getAllTasksOrderByUser() {
        return taskRepository.findAllByOrderByUserAsc();
    }

}
