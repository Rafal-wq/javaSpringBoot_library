package example.library.booklibrary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Value("${book.json.file-path}")
    private String jsonFilePath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Book> readBooksFromFile() {
        try {
            File file = new File(jsonFilePath);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Book>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeBooksToFile(List<Book> books) {
        try {
            objectMapper.writeValue(new File(jsonFilePath), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> filterBooks(String title, String author, Integer year, Double rating) {
        List<Book> books = readBooksFromFile();
        return books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title))
                        && (author == null || book.getAuthor().contains(author))
                        && (year == null || book.getYear() == year)
                        && (rating == null || book.getRating() >= rating))
                .collect(Collectors.toList());
    }

    public Book rateBook(Long id, int rating) {
        List<Book> books = readBooksFromFile();
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Nie znaleziono książki o ID: " + id));

        double newRating = (book.getRating() * book.getNumberOfRatings() + rating) / (book.getNumberOfRatings() + 1);
        book.setRating(newRating);
        book.setNumberOfRatings(book.getNumberOfRatings() + 1);

        writeBooksToFile(books);
        return book;
    }
}