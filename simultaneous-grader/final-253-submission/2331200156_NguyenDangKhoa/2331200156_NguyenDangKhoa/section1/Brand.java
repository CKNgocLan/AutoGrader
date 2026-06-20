
import java.util.UUID;

public class Brand {

    private UUID id = UUID.randomUUID();
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: %s".formatted(this.name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
