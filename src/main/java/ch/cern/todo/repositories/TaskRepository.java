package ch.cern.todo.repositories;

import ch.cern.todo.models.Task;
import ch.cern.todo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findAllByOrderByUserAsc();
}
