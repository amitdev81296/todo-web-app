package ch.cern.todo.controllers;

import ch.cern.todo.models.User;
import ch.cern.todo.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginFormController {

    private final Logger logger = LoggerFactory.getLogger(LoginFormController.class);
    private final UserRepository userRepository;

    public LoginFormController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String renderLoginForm(Model model) {
        model.addAttribute("error", "");
        model.addAttribute("message", "");
        return "login";
    }

    @GetMapping("/signup")
    public String renderSignupForm() {
        return "signup";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@NotNull String username, @NotNull String password, Model model) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "User does not exist!");
            return "login";
        }
    }

    @PostMapping("/signup")
    public String signupUser(@NotNull String firstname, @NotNull String lastname,
                             @NotNull String username, @NotNull String password,
                             @NotNull String email, Model model) {
        User user = new User(firstname, lastname, username, password, email, "USER");
        if(userRepository.findByUsername(username).isEmpty()) {
            userRepository.save(user);
        } else {
            model.addAttribute("error", "User with username: " + username + " already exists!");
            return "signup";
        }
        model.addAttribute("message", "User created. Please proceed to login!");
        return "login";
    }

}
