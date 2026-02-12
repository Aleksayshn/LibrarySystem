package entities;

import java.util.UUID;

public class Member {

    private final String id;
    private final String name;

    public Member(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
