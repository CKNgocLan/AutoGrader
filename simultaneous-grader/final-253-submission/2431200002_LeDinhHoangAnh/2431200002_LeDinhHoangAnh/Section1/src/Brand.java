import java.util.UUID;

public class Brand {
private UUID id;
private String name;

public Brand(String name) {
    this.name = name;
}
public void setId(UUID id) {
    this.id = id;
}

public UUID getId() {
    return id.randomUUID();
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
