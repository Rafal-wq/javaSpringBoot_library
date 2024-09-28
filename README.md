# Java Spring Boot Library

This project is a simple library application built using Java Spring Boot. It allows for book management, including adding ratings and filtering books.

## Requirements

- Java 11 or newer
- Maven
- Git

## How to Run the Application

1. Clone the repository:
   ```
   git clone https://github.com/Rafal-wq/javaSpringBoot_library.git
   ```

2. Navigate to the project directory:
   ```
   cd javaSpringBoot_library
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application should now be running at `http://localhost:8080`.

## How to Use the Application

The application provides the following endpoints:

### Retrieving Books

- `GET /books` - retrieves all books
- `GET /books?title={title}` - filters books by title
- `GET /books?author={author}` - filters books by author
- `GET /books?year={year}` - filters books by publication year
- `GET /books?rating={rating}` - filters books by minimum rating

### Adding Ratings

- `POST /books/{id}/rate` - adds a rating to a book with the given ID
  - Parameter: `rating` (value from 1 to 5)

Example usage (using curl):
```
curl -X POST "http://localhost:8080/books/1/rate?rating=4"
```

## Project Structure

- `src/main/java/example/library/booklibrary/` - main package with source code
  - `Book.java` - book model
  - `BookController.java` - controller handling HTTP requests
  - `BookRepository.java` - repository interface for database operations
  - `BookService.java` - service containing business logic
  - `BookLibraryApplication.java` - main application class

- `src/main/resources/`
  - `application.yml` - application configuration file

- `src/test/` - unit and integration tests

## How to Run Tests

To run the tests, use the following Maven command:

```
mvn test
```

## Configuration

The application configuration is located in the `src/main/resources/application.yml` file. You can change the server port, the path to the JSON file with book data, and logging levels there.

## Notes

- The application uses a JSON file as a database. Make sure the `books.json` file exists in the project root directory and has appropriate read and write permissions.
- In the current implementation, adding a rating to a book updates the book's average rating.

## Issues and Suggestions

If you encounter any problems or have suggestions for improvements, please create a new issue in this GitHub repository.
