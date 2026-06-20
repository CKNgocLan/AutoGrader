public class FountainPen extends Pen {
   public FountainPen(Brand brand, String model, Color color, double price) {
      super(brand, model, color);
      super.setPrice(price);
   }

   public String getDescription() {
      return String.format("Fountain Pen: %s %s (%s) - $%.2f", this.getBrand().getName(), this.getModel(), this.getColor(), this.getPrice());
   }
}
