
import java.util.UUID;

public class Brand {
    
    private UUID id = UUID.randomUUID();
    private String name;

    public Brand(String name) {
        this.name = name;

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
    
    @Override
    public String toString(){
        return name;
    }
}
