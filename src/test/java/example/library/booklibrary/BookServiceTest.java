package example.library.booklibrary;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@Test
	public void testFilterByTitle() {
		Book book = new Book("Clean Code", "Robert C. Martin", 2008, 4.8);
		List<Book> books = Collections.singletonList(book);

		Mockito.when(bookRepository.findByTitleContaining("Clean Code")).thenReturn(books);

		List<Book> result = bookService.filterBooks("Clean Code", null, null, null);
		assertThat(result).isNotEmpty();
		assertThat(result.getFirst().getTitle()).isEqualTo("Clean Code");
	}

	@Test
	public void testRateBook() {
		Book book = new Book("Effective Java", "Joshua Bloch", 2008, 4.5);

		Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		Book ratedBook = bookService.rateBook(1L, 5);
		assertThat(ratedBook.getRating()).isGreaterThan(4.5);
	}
}
