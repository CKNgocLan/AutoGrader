import java.util.UUID;

public class Brand {
    private UUID id;
    private String name;

    public Brand(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId(UUID id) {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
