public class FountainPenFactory implements PenFactory {
   public FountainPenFactory() {
   }

   public Pen createPen(Brand brand, String model, Color color, double price) {
      return new FountainPen(brand, model, color, price);
   }
}
