import java.util.UUID;

public class Brand extends Pen {
    private UUID id;
    private String name;
    public Brand (UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return"ID: %s + Name: %s".formatted(getId(), getName());
    }
}
