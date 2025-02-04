package ch.cern.todo.config;

import ch.cern.todo.models.Category;
import ch.cern.todo.models.Task;
import ch.cern.todo.repositories.CategoryRepository;
import ch.cern.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class TaskLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(TaskLoader.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPlaceholderData();
    }

    private void loadPlaceholderData() {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category("Groceries", "Buy groceries.");
            Category category2 = new Category("Bank Errands", "Bank related errands.");
            Category category3 = new Category("Family", "Family related errands.");
            Category category4 = new Category("Vehicle", "Vehicle(s) related errands.");
            Category category5 = new Category("Tax Returns", "Errands related to tax filing.");
            Category category6 = new Category("House", "Errands related to the house.");
            categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5, category6));
        }
    }
}
