
import java.util.UUID;

public class Brand {

    private UUID id;
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public Brand(UUID id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand [id=" + id + ", name=" + name + ", getId()=" + getId() + ", getName()=" + getName()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

}
