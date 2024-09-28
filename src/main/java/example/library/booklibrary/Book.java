package example.library.booklibrary;

public class Book {

    private Long id;
    private String title;
    private String author;
    private int year;
    private double rating;
    private int numberOfRatings;

    public Book() {
    }

    public Book(String title, String author, int year, double rating) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.numberOfRatings = 1;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                '}';
    }
}