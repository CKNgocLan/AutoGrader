package section1;

import java.util.*;

public class Brand {
    private UUID id;
    private String name;
    public Brand(String name) {

        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Brand [name=" + name + "]";
    }
}
