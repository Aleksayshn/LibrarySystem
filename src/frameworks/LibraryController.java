package frameworks;

import usecases.*;

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
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Available Books");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        addBookUseCase.execute(title, author);
                        System.out.println("Book added.");
                        break;

                    case "2":
                        System.out.print("Member Name: ");
                        String name = scanner.nextLine();
                        registerMemberUseCase.execute(name);
                        System.out.println("Member registered.");
                        break;

                    case "3":
                        System.out.print("Book ID: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Member ID: ");
                        String memberId = scanner.nextLine();
                        borrowBookUseCase.execute(bookId, memberId);
                        System.out.println("Book borrowed.");
                        break;

                    case "4":
                        System.out.print("Book ID: ");
                        String returnBookId = scanner.nextLine();
                        returnBookUseCase.execute(returnBookId);
                        System.out.println("Book returned.");
                        break;

                    case "5":
                        listBooksUseCase.execute().forEach(book ->
                                System.out.println(book.getId() + " | " +
                                        book.getTitle() + " | " +
                                        book.getAuthor()));
                        break;

                    case "0":
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
