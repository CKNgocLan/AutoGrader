import java.util.UUID;

public class Pen {
   private UUID id = UUID.randomUUID();
   private String brand;
   private String model;
   private double price;

   private Pen(PenBuilder builder) {
      this.brand = builder.brand;
      this.model = builder.model;
      this.price = builder.price;
   }

   public UUID getID() {
      return this.id;
   }

   public String getBrand() {
      return this.brand;
   }

   public String getModel() {
      return this.model;
   }

   public double getPrice() {
      return this.price;
   }

   public String toString() {
      String var10000 = String.valueOf(this.id);
      return "Pen{id=" + var10000 + "\n, brand='" + this.brand + "'\n, model='" + this.model + "'\n, price=" + this.price + "\n}";
   }

   public static class PenBuilder {
      private String brand;
      private String model;
      private double price;
   }
}
