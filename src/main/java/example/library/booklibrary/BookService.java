package example.library.booklibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> filterBooks(String title, String author, Integer year, Double rating) {
        return bookRepository.findAll().stream()
                .filter(book -> (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                        && (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                        && (year == null || book.getYear() == year)
                        && (rating == null || book.getRating() >= rating))
                .collect(Collectors.toList());
    }

    public Book rateBook(Long id, int rating) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Nie znaleziono książki o ID: " + id));

        double newRating = (book.getRating() * book.getNumberOfRatings() + rating) / (book.getNumberOfRatings() + 1);
        book.setRating(newRating);
        book.setNumberOfRatings(book.getNumberOfRatings() + 1);

        return bookRepository.save(book);
    }
}