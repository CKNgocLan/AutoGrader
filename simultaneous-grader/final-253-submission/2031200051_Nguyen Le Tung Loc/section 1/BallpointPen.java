public class BallpointPen extends Pen {
   public BallpointPen(Brand brand, String model, Color color, double price) {
      super(brand, model, color);
      this.setPrice(price);
   }

   /** @deprecated */
   @Deprecated
   public String getDescription() {
      return String.format("Ballpoint Pen: %s %s (%s) - $%.2f", this.getBrand().getName(), this.getModel(), this.getColor(), this.getPrice());
   }
}
