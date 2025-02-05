package ch.cern.todo.controllers;

import ch.cern.todo.models.Task;
import ch.cern.todo.models.User;
import ch.cern.todo.repositories.CategoryRepository;
import ch.cern.todo.repositories.TaskRepository;
import ch.cern.todo.repositories.UserRepository;
import ch.cern.todo.service.TaskService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;


@Controller
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping("/todos/adminview")
    public String indexAdmin(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksOrderByUser());
        return "admin-view";
    }

    @GetMapping("/todos/{userId}")
    public ModelAndView index(@PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        logger.debug("Index Page");
        ModelAndView modelAndView = new ModelAndView("index");
        List<Task> tasks = taskService.getTasksForUser(user);
        for (Task task : tasks) {
            logger.info(String.valueOf(task.getId()));
        }
        modelAndView.addObject("todoItems", tasks);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/todo/{userId}")
    public String createTask(@PathVariable("userId") long userId, @Valid Task task,
                             @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline,
                             @Nullable Long categoryId, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-todo-item";
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setTaskName(task.getTaskName());
        task.setTaskDescription(task.getTaskDescription());
        task.setDeadline(deadline);
        task.setUser(user);
        if(categoryId != null) {
            task.setCategory(categoryRepository.findById(categoryId).get());
        }
        taskRepository.save(task);
        return "redirect:/todos/" + user.getId();
    }

    @PostMapping("/todo/{userId}/{taskId}")
    public String updateTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId,
                             @Valid Task task,
                             @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline,
                             @Nullable Long categoryId, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            task.setId(taskId);
            return "update-todo-item";
        }
        Task task1 = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task id: " + taskId + " not found"));
        task1.setTaskName(task.getTaskName());
        task1.setDeadline(deadline);
        task1.setTaskDescription(task.getTaskDescription());
        if(categoryId != null) {
            task1.setCategory(categoryRepository.findById(categoryId).get());
        }
        taskRepository.save(task1);
        return "redirect:/todos/" + userId;
    }


}
