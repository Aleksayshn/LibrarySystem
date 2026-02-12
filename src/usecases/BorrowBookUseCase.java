package usecases;

import entities.Book;
import entities.BorrowRecord;

/**
 * Handles borrowing logic including business validation.
 */
public class BorrowBookUseCase {

    private static final int BORROW_LIMIT = 3;

    private final LibraryRepository repository;

    public BorrowBookUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public void execute(String bookId, String memberId) {

        Book book = repository.findBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book already borrowed.");
        }

        long activeBorrows = repository.countActiveBorrowsByMember(memberId);
        if (activeBorrows >= BORROW_LIMIT) {
            throw new IllegalStateException("Borrow limit exceeded (max 3 books).");
        }

        book.borrow();
        repository.saveBorrowRecord(new BorrowRecord(bookId, memberId));
    }
}
