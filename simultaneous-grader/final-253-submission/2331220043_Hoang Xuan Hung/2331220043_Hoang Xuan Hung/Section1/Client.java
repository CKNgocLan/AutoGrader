public class Client {
 private Pen pen;
 public Client(PenFactory factory, Brand brand, String model, Color color, double price) {
     pen = factory.createPen(brand, model, color, price);
 }
    public Pen getPen() {
        return pen;
    }
  
}
