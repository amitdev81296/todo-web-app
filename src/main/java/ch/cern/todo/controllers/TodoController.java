package ch.cern.todo.controllers;

import ch.cern.todo.models.Task;
import ch.cern.todo.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("Index Page");
        ModelAndView modelAndView = new ModelAndView("index");
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        for (Task task : tasks) {
            logger.info(String.valueOf(task.getId()));
        }
        modelAndView.addObject("todoItems", tasks);
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTask(@PathVariable("id") long id, @Valid Task task, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            task.setId(id);
            return "update-todo-item";
        }
        task.setTaskName(task.getTaskName());
        task.setDeadline(task.getDeadline());
        task.setTaskDescription(task.getTaskDescription());
        taskRepository.save(task);
        return "redirect:/";
    }


}
