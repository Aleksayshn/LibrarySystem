package usecases;

import entities.Member;

/**
 * Handles registering a new library member.
 */
public class RegisterMemberUseCase {

    private final LibraryRepository repository;

    public RegisterMemberUseCase(LibraryRepository repository) {
        this.repository = repository;
    }

    public void execute(String name) {
        Member member = new Member(name);
        repository.saveMember(member);
    }
}
