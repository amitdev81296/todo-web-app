package ch.cern.todo.repositories;

import ch.cern.todo.models.Task;
import ch.cern.todo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
