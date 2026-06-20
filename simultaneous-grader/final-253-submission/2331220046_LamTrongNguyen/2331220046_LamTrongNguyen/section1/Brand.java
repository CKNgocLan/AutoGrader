

import java.util.UUID;

public class Brand {
    private UUID id;
    private String name;
    private String country;
    public Brand(String name) {
        this.id=UUID.randomUUID();
        this.name = name;
        this.country=country;
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString(){
        return name;
    }
    
}
