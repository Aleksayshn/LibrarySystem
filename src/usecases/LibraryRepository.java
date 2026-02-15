package usecases;

import entities.Book;
import entities.Member;
import entities.BorrowRecord;

import java.util.List;
import java.util.Optional;

/**
 * Shared repository abstraction used by all use cases.
 */
public interface LibraryRepository {

    void saveBook(Book book);

    void saveMember(Member member);

    Optional<Book> findBookById(String id);

    Optional<Member> findMemberById(String id);

    List<Member> findMembersByLastName(String lastName);

    List<Book> findAllBooks();

    void saveBorrowRecord(BorrowRecord record);

    Optional<BorrowRecord> findActiveBorrowRecordByBookId(String bookId);

    long countActiveBorrowsByMember(String memberId);
}
