// package section1;

import java.util.UUID;

public class Brand {
    private String name;
    private UUID id;

    public Brand(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setID(UUID id) {
        this.id = id;
    }
}
