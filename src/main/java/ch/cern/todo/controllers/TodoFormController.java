package ch.cern.todo.controllers;

import ch.cern.todo.models.Task;
import ch.cern.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoFormController {

    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));
        model.addAttribute("task", task);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task id: " + id + " not found"));
        taskRepository.delete(task);
        return "redirect:/";
    }

}
