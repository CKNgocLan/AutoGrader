public interface PenFactory {
    Pen createPen();

    Pen createPen(Brand brand, String model, Color color, double price);

    Pen createPen(Brand brand, String model, Color color);
}
