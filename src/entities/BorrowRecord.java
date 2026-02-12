package entities;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowRecord {

    private final String id;
    private final String bookId;
    private final String memberId;
    private final LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String bookId, String memberId) {
        this.id = UUID.randomUUID().toString();
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markReturned() {
        this.returnDate = LocalDate.now();
    }
}
