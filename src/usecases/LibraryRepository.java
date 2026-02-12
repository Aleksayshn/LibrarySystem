package usecases;

import entities.Book;
import entities.Member;
import entities.BorrowRecord;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for library data persistence.
 * Following Dependency Inversion Principle.
 */
public interface LibraryRepository {

    void saveBook(Book book);

    void saveMember(Member member);

    Optional<Book> findBookById(String id);

    Optional<Member> findMemberById(String id);

    List<Book> findAllBooks();

    void saveBorrowRecord(BorrowRecord record);

    Optional<BorrowRecord> findActiveBorrowRecordByBookId(String bookId);

    long countActiveBorrowsByMember(String memberId);
}
