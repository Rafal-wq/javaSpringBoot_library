package example.library.booklibrary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    @Value("${book.json.file-path}")
    private String jsonFilePath;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Book> books;

    @PostConstruct
    public void init() {
        this.books = readBooksFromFile();
    }

    public void deleteAll() {
        books.clear();
        writeBooksToFile();
    }

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

    private void writeBooksToFile() {
        try {
            objectMapper.writeValue(new File(jsonFilePath), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId((long) (books.size() + 1));
            books.add(book);
        } else {
            books.removeIf(b -> b.getId().equals(book.getId()));
            books.add(book);
        }
        writeBooksToFile();
        return book;
    }

    public List<Book> findByTitleContaining(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthorContaining(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findByYear(Integer year) {
        return books.stream()
                .filter(b -> year == null || b.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> findByRatingGreaterThanEqual(Double rating) {
        return books.stream()
                .filter(b -> b.getRating() >= rating)
                .collect(Collectors.toList());
    }
}