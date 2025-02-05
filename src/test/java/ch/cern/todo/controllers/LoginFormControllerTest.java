package ch.cern.todo.controllers;

import ch.cern.todo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginFormController.class)
@ExtendWith(MockitoExtension.class)
public class LoginFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    private LoginFormController loginFormController;

    @Test
    public void testRenderLoginForm() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(content().string("login"));
    }

}
