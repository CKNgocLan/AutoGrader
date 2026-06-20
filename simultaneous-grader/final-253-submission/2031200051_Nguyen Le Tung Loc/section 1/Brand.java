
import java.util.UUID;

public class Brand {
   private final UUID id = UUID.randomUUID();
   private String name;

   public Brand(String name) {
      this.name = name;
   }

   public UUID getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      String var10000 = String.valueOf(this.id);
      return "Brand{id=" + var10000 + "\n, name='" + this.name + "'\n}";
   }
}
