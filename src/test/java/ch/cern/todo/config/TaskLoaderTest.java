package ch.cern.todo.config;

import ch.cern.todo.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskLoaderTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TaskLoader taskLoader;

    @Test
    public void testRun() throws Exception {
        taskLoader.run();
        verify(categoryRepository, times(1)).saveAll(anyList());
    }

}
