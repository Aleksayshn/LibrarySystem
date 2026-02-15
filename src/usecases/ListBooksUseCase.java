package usecases;

import entities.Book;

import java.util.List;

/**
 * Returns all available books.
 */
public class ListBooksUseCase {

    private final LibraryRepository repository;

    public ListBooksUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public List<Book> execute() {
        return repository.findAllBooks()
                .stream()
                .filter(Book::isAvailable)
                .toList();
    }
}
