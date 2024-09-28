package example.library.booklibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        Book book1 = new Book("Clean Code", "Robert C. Martin", 2008, 4.8);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 2008, 4.5);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Clean Code")));
    }

    @Test
    public void testRateBook() throws Exception {
        mockMvc.perform(post("/books/1/rate")
                        .param("rating", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating", greaterThan(4.5)));
    }
}