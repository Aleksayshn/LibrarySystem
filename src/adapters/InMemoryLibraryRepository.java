package adapters;

import entities.Book;
import entities.Member;
import entities.BorrowRecord;
import usecases.LibraryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation using ArrayLists.
 */
public class InMemoryLibraryRepository implements LibraryRepository {

    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    @Override
    public void saveBook(Book book) {
        books.add(book);
    }

    @Override
    public void saveMember(Member member) {
        members.add(member);
    }

    @Override
    public Optional<Book> findBookById(String id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Member> findMemberById(String id) {
        return members.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findMembersByLastName(String lastName) {
        return members.stream()
                .filter(m -> m.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    @Override
    public List<Book> findAllBooks() {
        return List.copyOf(books);
    }

    @Override
    public void saveBorrowRecord(BorrowRecord record) {
        borrowRecords.add(record);
    }

    @Override
    public Optional<BorrowRecord> findActiveBorrowRecordByBookId(String bookId) {
        return borrowRecords.stream()
                .filter(r -> r.getBookId().equals(bookId) && !r.isReturned())
                .findFirst();
    }

    @Override
    public long countActiveBorrowsByMember(String memberId) {
        return borrowRecords.stream()
                .filter(r -> r.getMemberId().equals(memberId) && !r.isReturned())
                .count();
    }
}
