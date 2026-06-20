import java.util.UUID;

public class Brand {
    public UUID id;
    public String name;

    public Brand (String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
