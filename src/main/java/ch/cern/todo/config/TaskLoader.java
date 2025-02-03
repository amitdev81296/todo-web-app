package ch.cern.todo.config;

import ch.cern.todo.models.Task;
import ch.cern.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TaskLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(TaskLoader.class);

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPlaceholderData();
    }

    private void loadPlaceholderData() {
        if (taskRepository.count() == 0) {
            Task task1 = new Task("Get the milk.", "Buy a gallon of milk at Trader Joe's", Instant.now().plus(1, ChronoUnit.DAYS));
            Task task2 = new Task("Rake the leaves.", "Rake the leaves in the front yard.", Instant.now().plus(2, ChronoUnit.DAYS));
            taskRepository.save(task1);
            taskRepository.save(task2);
        }
        logger.info("No. of Todo Items = {}", taskRepository.count());
    }
}
