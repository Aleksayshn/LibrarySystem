package entities;

import java.util.UUID;

public class Book {

    private final String id;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String title, String author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    /**
     * Marks book as borrowed.
     */
    public void borrow() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        this.available = false;
    }

    /**
     * Marks book as returned.
     */
    public void returnBook() {
        this.available = true;
    }
}
