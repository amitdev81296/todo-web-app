package ch.cern.todo.repositories;

import ch.cern.todo.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
