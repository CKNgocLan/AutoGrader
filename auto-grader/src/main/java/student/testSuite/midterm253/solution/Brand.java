package student.testSuite.midterm253.solution;

import java.util.UUID;

public class Brand {
    private UUID id;
    private String name;
    private String country;

    public Brand(String name, String country) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.country = country;
    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "%s - %s".formatted(getName(), getCountry());
    }
}
