# LibrarySystem

## SOLID Principles In This Project

### S - Single Responsibility Principle (SRP)
- `Book`, `Member`, and `BorrowRecord` in `src/entities/` only model domain state and behavior.
- Each use case in `src/usecases/` handles one business operation:
  - `AddBookUseCase` adds books.
  - `RegisterMemberUseCase` registers members.
  - `BorrowBookUseCase` performs borrow validation and borrowing.
  - `ReturnBookUseCase` handles return flow.
  - `ListBooksUseCase` returns available books.
- `LibraryController` in `src/frameworks/LibraryController.java` handles console interaction and delegates business logic to use cases.
- `InMemoryLibraryRepository` in `src/adapters/InMemoryLibraryRepository.java` handles persistence details using `ArrayList`.

### O - Open/Closed Principle (OCP)
- New persistence mechanisms can be added by creating another implementation of `LibraryRepository` without changing use-case logic.
- Controller actions are organized as command handlers in a map (`registerActions`), so adding a menu command is localized and does not require rewriting core flow logic.

### L - Liskov Substitution Principle (LSP)
- All use cases depend on `LibraryRepository` and can work with any valid repository implementation (in-memory now, database later) as long as behavior matches the interface contract.
- `findAllBooks()` returns a defensive copy (`List.copyOf`) to prevent callers from mutating internal storage and violating repository invariants.

### I - Interface Segregation Principle (ISP)
- The assignment requires one shared repository interface (`LibraryRepository`) used by all use cases.
- Because of that requirement, strict ISP is only partially applied: some use cases depend on methods they do not directly call.
- If assignment constraints were removed, this interface could be split into smaller ports per use case.

### D - Dependency Inversion Principle (DIP)
- High-level business rules (use cases) depend on the `LibraryRepository` abstraction, not on `InMemoryLibraryRepository` directly.
- Concrete wiring happens in `src/main/Main.java`, where implementations are injected into use cases.

## Additional Feature
- Borrowing limit is implemented in `BorrowBookUseCase` with `BORROW_LIMIT = 3`.
- Validation uses `countActiveBorrowsByMember` before allowing a new borrow.

## Reflection
- The architecture cleanly separates entities, application logic, adapters, and framework code, which made changes straightforward and test-friendly.
- The biggest design compromise is the single shared repository interface: it satisfies assignment rules but is broader than ideal from a pure ISP perspective.
- The controller is still simple and procedural by design, which is appropriate for a small console project.
- Next improvements would be adding automated tests for each use case, adding input validation at the boundary, and introducing a persistent repository implementation while keeping use cases unchanged.
