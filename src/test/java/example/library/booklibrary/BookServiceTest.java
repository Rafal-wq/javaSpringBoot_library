package example.library.booklibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFilterByTitle() {
		Book book = new Book("Clean Code", "Robert C. Martin", 2008, 4.8);
		when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

		List<Book> result = bookService.filterBooks("Clean Code", null, null, null);

		assertThat(result).isNotEmpty();
		assertThat(result.get(0).getTitle()).isEqualTo("Clean Code");
	}

	@Test
	void testRateBook() {
		Book book = new Book("Effective Java", "Joshua Bloch", 2008, 4.5);
		book.setId(1L);
		book.setNumberOfRatings(1);

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Book ratedBook = bookService.rateBook(1L, 5);

		assertThat(ratedBook).isNotNull();
		assertThat(ratedBook.getRating()).isGreaterThan(4.5);
		assertThat(ratedBook.getNumberOfRatings()).isEqualTo(2);
	}
}