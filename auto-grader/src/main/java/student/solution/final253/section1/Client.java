package student.solution.final253.section1;

public class Client {
    private Pen pen;

    public Client(PenFactory penFactory, Brand brand, String model, Color color, double price) {
        this.pen = penFactory.createPen(brand, model, color, price);
    }

    public Pen getPen() {
        return pen;
    }
}
