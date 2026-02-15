package frameworks;

import usecases.AddBookUseCase;
import usecases.BorrowBookUseCase;
import usecases.ListBooksUseCase;
import usecases.RegisterMemberUseCase;
import usecases.ReturnBookUseCase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles user input and delegates to use cases.
 */
public class LibraryController {

    private final AddBookUseCase addBookUseCase;
    private final RegisterMemberUseCase registerMemberUseCase;
    private final BorrowBookUseCase borrowBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;
    private final ListBooksUseCase listBooksUseCase;
    private final Map<String, MenuAction> actions = new LinkedHashMap<>();

    public LibraryController(AddBookUseCase addBookUseCase,
                             RegisterMemberUseCase registerMemberUseCase,
                             BorrowBookUseCase borrowBookUseCase,
                             ReturnBookUseCase returnBookUseCase,
                             ListBooksUseCase listBooksUseCase) {

        this.addBookUseCase = addBookUseCase;
        this.registerMemberUseCase = registerMemberUseCase;
        this.borrowBookUseCase = borrowBookUseCase;
        this.returnBookUseCase = returnBookUseCase;
        this.listBooksUseCase = listBooksUseCase;
        registerActions();
    }

    private void registerActions() {
        actions.put("1", new MenuAction("Add Book", this::addBook));
        actions.put("2", new MenuAction("Register Member", this::registerMember));
        actions.put("3", new MenuAction("Borrow Book", this::borrowBook));
        actions.put("4", new MenuAction("Return Book", this::returnBook));
        actions.put("5", new MenuAction("List Available Books", this::listAvailableBooks));
        actions.put("0", new MenuAction("Exit", scanner -> System.exit(0)));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            actions.forEach((key, action) -> System.out.println(key + ". " + action.label()));

            String choice = scanner.nextLine();

            try {
                MenuAction action = actions.get(choice);
                if (action == null) {
                    System.out.println("Invalid option.");
                    continue;
                }
                action.handler().run(scanner);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addBook(Scanner scanner) {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        addBookUseCase.execute(title, author);
        System.out.println("Book added.");
    }

    private void registerMember(Scanner scanner) {
        System.out.print("Member Name: ");
        String name = scanner.nextLine();
        registerMemberUseCase.execute(name);
        System.out.println("Member registered.");
    }

    private void borrowBook(Scanner scanner) {
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();
        borrowBookUseCase.execute(bookId, memberId);
        System.out.println("Book borrowed.");
    }

    private void returnBook(Scanner scanner) {
        System.out.print("Book ID: ");
        String returnBookId = scanner.nextLine();
        returnBookUseCase.execute(returnBookId);
        System.out.println("Book returned.");
    }

    private void listAvailableBooks(Scanner scanner) {
        listBooksUseCase.execute().forEach(book ->
                System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getAuthor()));
    }

    private record MenuAction(String label, MenuHandler handler) {
    }

    @FunctionalInterface
    private interface MenuHandler {
        void run(Scanner scanner);
    }
}
