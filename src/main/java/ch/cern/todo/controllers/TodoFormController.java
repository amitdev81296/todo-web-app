package ch.cern.todo.controllers;

import ch.cern.todo.models.Category;
import ch.cern.todo.models.Task;
import ch.cern.todo.models.User;
import ch.cern.todo.repositories.CategoryRepository;
import ch.cern.todo.repositories.TaskRepository;
import ch.cern.todo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TodoFormController {

    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create-todo/{userId}")
    public String showCreateForm(@PathVariable("userId") long userId, Task task, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("categories", categories);
        model.addAttribute("user", user);
        return "add-todo-item";
    }

    @GetMapping("/edit/{userId}/{taskId}")
    public String showEditForm(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task id: " + taskId + " not found"));
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        return "update-todo-item";
    }

    @GetMapping("/delete/{userId}/{taskId}")
    public String deleteTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, Model model) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task id: " + taskId + " not found"));
        taskRepository.delete(task);
        return "redirect:/todos/" + userId;
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("message", "You have been logged out");
        return "login";
    }

}
