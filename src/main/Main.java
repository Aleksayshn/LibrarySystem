package main;

import adapters.InMemoryLibraryRepository;
import frameworks.LibraryController;
import usecases.AddBookUseCase;
import usecases.BorrowBookUseCase;
import usecases.ListBooksUseCase;
import usecases.RegisterMemberUseCase;
import usecases.ReturnBookUseCase;

public class Main {

    public static void main(String[] args) {

        InMemoryLibraryRepository repository = new InMemoryLibraryRepository();

        AddBookUseCase addBookUseCase = new AddBookUseCase(repository);
        RegisterMemberUseCase registerMemberUseCase = new RegisterMemberUseCase(repository);
        BorrowBookUseCase borrowBookUseCase = new BorrowBookUseCase(repository);
        ReturnBookUseCase returnBookUseCase = new ReturnBookUseCase(repository);
        ListBooksUseCase listBooksUseCase = new ListBooksUseCase(repository);

        LibraryController controller = new LibraryController(
                addBookUseCase,
                registerMemberUseCase,
                borrowBookUseCase,
                returnBookUseCase,
                listBooksUseCase
        );

        controller.start();
    }
}
