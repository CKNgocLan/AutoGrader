public class Client {
    private Pen pen;

    public Client(Pen pen) {
        BallpointPenFactory.createPen ballpointPenClient = new BallpointPenFactory.createPen();
        FountainPenFactory.createPen fountainPenClient = new FountainPenFactory.createPen();
    }

    public Pen getPen() {
        return pen;
    }
}
