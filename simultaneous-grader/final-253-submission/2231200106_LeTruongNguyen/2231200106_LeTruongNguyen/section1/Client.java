

public abstract class Client {
    protected Pen pen;

    public Client(Pen pen) {
        this.pen = pen;
    }

    public abstract Pen getPen();

}
