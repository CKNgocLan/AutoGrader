public class Client {
    private Pen pen;

    public Client(Pen pen, PenFactory penFactory) {
        this.pen = penFactory.createPen(pen.getBrand(), pen.getModel(), pen.getColor(), pen.getPrice());
    }

    public Pen getPen(){
        return this.pen;
    }
}
