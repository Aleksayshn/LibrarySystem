package usecases;

import entities.Member;

import java.util.List;

/**
 * Finds members by last name to help users choose the exact member ID.
 */
public class FindMembersByLastNameUseCase {

    private final LibraryRepository repository;

    public FindMembersByLastNameUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public List<Member> execute(String lastName) {
        return repository.findMembersByLastName(lastName);
    }
}
