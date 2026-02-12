package usecases;

import entities.Book;

/**
 * Handles adding a new book to the system.
 */
public class AddBookUseCase {

    private final LibraryRepository repository;

    public AddBookUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public void execute(String title, String author) {
        Book book = new Book(title, author);
        repository.saveBook(book);
    }
}
