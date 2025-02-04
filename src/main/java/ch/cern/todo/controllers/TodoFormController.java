package ch.cern.todo.controllers;

import ch.cern.todo.models.Category;
import ch.cern.todo.models.Task;
import ch.cern.todo.repositories.CategoryRepository;
import ch.cern.todo.repositories.TaskRepository;
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

    @GetMapping("/create-todo")
    public String showCreateForm(Task task, Model model) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("categories", categories);
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("categories", categories);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));
        taskRepository.delete(task);
        return "redirect:/";
    }

}
