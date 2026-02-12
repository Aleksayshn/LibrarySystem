package usecases;

import entities.Book;
import entities.BorrowRecord;

/**
 * Handles returning a borrowed book.
 */
public class ReturnBookUseCase {

    private final LibraryRepository repository;

    public ReturnBookUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public void execute(String bookId) {

        Book book = repository.findBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        BorrowRecord record = repository.findActiveBorrowRecordByBookId(bookId)
                .orElseThrow(() -> new IllegalStateException("No active borrow record found."));

        book.returnBook();
        record.markReturned();
    }
}
