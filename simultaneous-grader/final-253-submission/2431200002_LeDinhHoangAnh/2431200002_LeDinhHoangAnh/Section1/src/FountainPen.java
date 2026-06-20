public class FountainPen extends Pen{
    private Pen pen;

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color);
        this.pen.setPrice(price);
    }

    
    
    @Override
    public String getDescription() {
        return "Fountain Pen: <%s>-<%s>-<%s>-<%s>".formatted(pen.getBrand().getName(),pen.getModel(),pen.getColor(),pen.getPrice());
    }
}
