
public class Client {
    private PenFactory penFactory;

    public Client(PenFactory pf) {
        this.penFactory = pf;
    }

    public Pen getPen() {
        return this.penFactory.createPen();
    }

}
